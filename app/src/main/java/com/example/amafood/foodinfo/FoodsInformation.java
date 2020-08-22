package com.example.amafood.foodinfo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.amafood.R;
import com.example.amafood.calender.food.datebase.CalenderFoodDataBase;
import com.example.amafood.calender.food.datebase.CalenderFoodDeo;
import com.example.amafood.calender.food.datebase.CalenderFoods;
import com.example.amafood.category.CategoryRetrofitClient;
import com.example.amafood.categoryfoods.CategoryFoodsItemsListDataClass;
import com.example.amafood.categoryfoods.CategoryFoodsListDataClass;
import com.example.amafood.categoryfoods.CategoryFoodsListRecycleAdapter;
import com.example.amafood.categoryfoods.FoodsListApiService;
import com.example.amafood.databinding.FragmentFoodsInformationBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class FoodsInformation extends Fragment {
    FragmentFoodsInformationBinding binding;
    String mealName;
    FoodInfoParentDataClass foodInfo;
    List<FoodMaterialDataClass> foodMaterial;
    MediaController mediaController;
    Boolean goneRecycle;
    Boolean chooseFood;
    String currentDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_foods_information, container, false);

        getData();
        setToolbar();
        return binding.getRoot();
    }

    @SuppressLint("RestrictedApi")
    private void getChooseFood() {
        chooseFood = getArguments().getBoolean("chooseFood");
        currentDate = getArguments().getString("date");
        if (chooseFood && currentDate != null) {
            binding.addFood.setVisibility(View.VISIBLE);
            binding.rvMoreFoodsLayout.setVisibility(View.GONE);
            Toast.makeText(getContext(), currentDate, Toast.LENGTH_SHORT).show();
            binding.addFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CalenderFoods calenderFoods = new CalenderFoods(currentDate, foodMaterial.get(0).getMealName(), foodMaterial.get(0).getMealImg());
                    CalenderFoodDeo calenderFoodDeo = CalenderFoodDataBase.getInstance(getContext()).calenderFoodDeo();
                    long result = calenderFoodDeo.insertFood(calenderFoods);
                    Toast.makeText(getContext(), "foodSaved"+result, Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.action_foodsInformation_to_homePageFragment);
                }
            });
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_refresh_food_info:
                getData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setMoreFoodsRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvMoreFoods.setLayoutManager(linearLayoutManager);
    }
    private void getMoreFood() {
        String category = foodMaterial.get(0).getMealCategory();
        FoodsListApiService foodsListApiService = CategoryRetrofitClient.getRetrofit().create(FoodsListApiService.class);
        Call<CategoryFoodsListDataClass> call = foodsListApiService.getCategoryFoodsList(category);
        call.enqueue(new Callback<CategoryFoodsListDataClass>() {
            @Override
            public void onResponse(Call<CategoryFoodsListDataClass> call, Response<CategoryFoodsListDataClass> response) {
                goneRecycle = getArguments().getBoolean("goneRecycle");
                if (goneRecycle) {
                    binding.rvMoreFoodsLayout.setVisibility(View.GONE);
                }
                initMoreFoodsRecycle(response.body());
            }

            @Override
            public void onFailure(Call<CategoryFoodsListDataClass> call, Throwable t) {

            }
        });
    }
    private void initMoreFoodsRecycle(CategoryFoodsListDataClass categoryFoodsListDataClass) {
        MoreFoodsRecycleAdapter moreFoodsRecycleAdapter = new MoreFoodsRecycleAdapter(categoryFoodsListDataClass.getMeals(), getContext());
        binding.rvMoreFoods.setAdapter(moreFoodsRecycleAdapter);
    }
    private void setData() {
        setVideo();
        Glide.with(getContext()).load(foodMaterial.get(0).getMealImg()).into(binding.imgFoodMaterial);
        binding.tvMealName.setText(foodMaterial.get(0).getMealName());
        binding.foodInstruction.setText(foodMaterial.get(0).getMealInstruction());
        binding.tvArea.setText("Area :\n"+foodMaterial.get(0).getMealArea());
        binding.tvCategory.setText("Category :\n"+foodMaterial.get(0).getMealCategory());
        setIng();
        setMeas();
        setMoreFoodsRecycle();
        getMoreFood();
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void setVideo() {
        if (foodMaterial.get(0).getMealVideo() != null) {
            binding.cardView4.setVisibility(View.VISIBLE);
            String videoUrl = foodMaterial.get(0).getMealVideo();
            videoUrl = videoUrl.replace("watch?v=", "embed/");
            String newVideoUrl = "<iframe width=\"325\" height=\"315\"" +
                    " " + " src=" + "\"" + videoUrl + "\"" + " " + "frameborder=\"0\"" + "allowfullscreen></iframe>";
            binding.videoView.setWebViewClient(new WebViewClient());
            WebSettings webSettings = binding.videoView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            binding.videoView.loadData(newVideoUrl, "text/html", null);
        }
    }
    private void showCards() {
        binding.cardView1.setVisibility(View.VISIBLE);
        binding.cardView2.setVisibility(View.VISIBLE);
        binding.cardView3.setVisibility(View.VISIBLE);
    }
    private void getData() {
        mealName = getArguments().getString("mealName");
        FoodInfoApiService foodInfoApiService = CategoryRetrofitClient.getRetrofit().create(FoodInfoApiService.class);
        Call<FoodInfoParentDataClass> call = foodInfoApiService.getFoodsInfo(mealName);
        call.enqueue(new Callback<FoodInfoParentDataClass>() {
            @Override
            public void onResponse(Call<FoodInfoParentDataClass> call, Response<FoodInfoParentDataClass> response) {
                foodInfo = response.body();
                if (foodInfo != null) {
                    foodMaterial = foodInfo.getFoodMaterial();
                    showCards();
                    setData();
                    setVisibility();
                    getChooseFood();
                }
            }

            @Override
            public void onFailure(Call<FoodInfoParentDataClass> call, Throwable t) {

            }
        });
    }
    private void setVisibility() {
        binding.infoLoading.setVisibility(View.GONE);
        binding.infoLayout.setVisibility(View.VISIBLE);
    }
    private void setToolbar() {
        binding.toolbar2.setTitle(mealName);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar2);
        setHasOptionsMenu(true);
        binding.toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_foodsInformation_to_homePageFragment);
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.food_info_over_flow_menu, menu);
    }
    private void setMeas() {
        if (foodMaterial.get(0).getIng1().contentEquals("") || foodMaterial.get(0).getIng1() == null) {
            binding.ing1.setVisibility(View.GONE);
            binding.l1.setVisibility(View.GONE);
        } else {
            binding.ing1.setText(foodMaterial.get(0).getIng1());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng1()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng1);
        }
        if (foodMaterial.get(0).getIng2().contentEquals("") || foodMaterial.get(0).getIng2() == null) {
            binding.ing2.setVisibility(View.GONE);
            binding.l2.setVisibility(View.GONE);
        } else {
            binding.ing2.setText(foodMaterial.get(0).getIng2());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng2()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng2);
        }
        if (foodMaterial.get(0).getIng3().contentEquals("") || foodMaterial.get(0).getIng3() == null) {
            binding.ing3.setVisibility(View.GONE);
            binding.l3.setVisibility(View.GONE);
        } else {
            binding.ing3.setText(foodMaterial.get(0).getIng3());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng3()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng3);
        }
        if (foodMaterial.get(0).getIng4().contentEquals("") || foodMaterial.get(0).getIng4() == null) {
            binding.ing4.setVisibility(View.GONE);
            binding.l4.setVisibility(View.GONE);
        } else {
            binding.ing4.setText(foodMaterial.get(0).getIng4());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng4()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng4);
        }
        if (foodMaterial.get(0).getIng5().contentEquals("") || foodMaterial.get(0).getIng5() == null) {
            binding.ing5.setVisibility(View.GONE);
            binding.l5.setVisibility(View.GONE);
        } else {
            binding.ing5.setText(foodMaterial.get(0).getIng5());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng5()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng5);
        }
        if (foodMaterial.get(0).getIng6().contentEquals("") || foodMaterial.get(0).getIng6() == null) {
            binding.ing6.setVisibility(View.GONE);
            binding.l6.setVisibility(View.GONE);
        } else {
            binding.ing6.setText(foodMaterial.get(0).getIng6());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng6()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng6);
        }
        if (foodMaterial.get(0).getIng7().contentEquals("") || foodMaterial.get(0).getIng7() == null) {
            binding.ing7.setVisibility(View.GONE);
            binding.l7.setVisibility(View.GONE);
        } else {
            binding.ing7.setText(foodMaterial.get(0).getIng7());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng7()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng7);
        }
        if (foodMaterial.get(0).getIng8().contentEquals("") || foodMaterial.get(0).getIng8() == null) {
            binding.ing8.setVisibility(View.GONE);
            binding.l8.setVisibility(View.GONE);
        } else {
            binding.ing8.setText(foodMaterial.get(0).getIng8());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng8()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng8);
        }
        if (foodMaterial.get(0).getIng9().contentEquals("") || foodMaterial.get(0).getIng9() == null) {
            binding.ing9.setVisibility(View.GONE);
            binding.l9.setVisibility(View.GONE);
        } else {
            binding.ing9.setText(foodMaterial.get(0).getIng9());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng9()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng9);
        }
        if (foodMaterial.get(0).getIng10().contentEquals("") || foodMaterial.get(0).getIng10() == null) {
            binding.ing10.setVisibility(View.GONE);
            binding.l10.setVisibility(View.GONE);
        } else {
            binding.ing10.setText(foodMaterial.get(0).getIng10());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng10()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng10);
        }
        if (foodMaterial.get(0).getIng11().contentEquals("") || foodMaterial.get(0).getIng11() == null) {
            binding.ing11.setVisibility(View.GONE);
            binding.l11.setVisibility(View.GONE);
        } else {
            binding.ing11.setText(foodMaterial.get(0).getIng11());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng11()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng11);
        }
        if (foodMaterial.get(0).getIng12().contentEquals("") || foodMaterial.get(0).getIng12() == null) {
            binding.ing12.setVisibility(View.GONE);
            binding.l12.setVisibility(View.GONE);
        } else {
            binding.ing12.setText(foodMaterial.get(0).getIng12());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng12()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng12);
        }
        if (foodMaterial.get(0).getIng13().contentEquals("") || foodMaterial.get(0).getIng13() == null) {
            binding.ing13.setVisibility(View.GONE);
            binding.l13.setVisibility(View.GONE);
        } else {
            binding.ing13.setText(foodMaterial.get(0).getIng13());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng13()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng13);
        }
        if (foodMaterial.get(0).getIng14().contentEquals("") || foodMaterial.get(0).getIng14() == null) {
            binding.ing14.setVisibility(View.GONE);
            binding.l14.setVisibility(View.GONE);
        } else {
            binding.ing14.setText(foodMaterial.get(0).getIng14());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng14()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng14);
        }
        if (foodMaterial.get(0).getIng15().contentEquals("") || foodMaterial.get(0).getIng15() == null) {
            binding.ing15.setVisibility(View.GONE);
            binding.l15.setVisibility(View.GONE);
        } else {
            binding.ing15.setText(foodMaterial.get(0).getIng15());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng15()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng15);
        }
        if (foodMaterial.get(0).getIng16().contentEquals("") || foodMaterial.get(0).getIng16() == null) {
            binding.ing16.setVisibility(View.GONE);
            binding.l16.setVisibility(View.GONE);
        } else {
            binding.ing16.setText(foodMaterial.get(0).getIng16());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng16()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng16);
        }
        if (foodMaterial.get(0).getIng17().contentEquals("") || foodMaterial.get(0).getIng17() == null) {
            binding.ing17.setVisibility(View.GONE);
            binding.l17.setVisibility(View.GONE);
        } else {
            binding.ing17.setText(foodMaterial.get(0).getIng17());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng17()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng17);
        }
        if (foodMaterial.get(0).getIng18().contentEquals("") || foodMaterial.get(0).getIng18() == null) {
            binding.ing18.setVisibility(View.GONE);
            binding.l18.setVisibility(View.GONE);
        } else {
            binding.ing18.setText(foodMaterial.get(0).getIng18());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng18()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng18);
        }
        if (foodMaterial.get(0).getIng19().contentEquals("") || foodMaterial.get(0).getIng19() == null) {
            binding.ing19.setVisibility(View.GONE);
            binding.l19.setVisibility(View.GONE);
        } else {
            binding.ing19.setText(foodMaterial.get(0).getIng19());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng19()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng19);
        }
        if (foodMaterial.get(0).getIng20().contentEquals("") || foodMaterial.get(0).getIng20() == null) {
            binding.ing20.setVisibility(View.GONE);
            binding.l20.setVisibility(View.GONE);
        } else {
            binding.ing20.setText(foodMaterial.get(0).getIng20());
            String imageUrl = "https://www.themealdb.com/images/ingredients/"+foodMaterial.get(0).getIng20()+".png";
            Glide.with(getContext()).load(imageUrl).into(binding.imgIng20);
        }

    }
    private void setIng() {
        if (foodMaterial.get(0).getMeas1().contentEquals("") || foodMaterial.get(0).getMeas1() == null) {
            binding.meas1.setVisibility(View.GONE);
        } else {
            binding.meas1.setText(foodMaterial.get(0).getMeas1());
        }
        if (foodMaterial.get(0).getMeas2().contentEquals("") || foodMaterial.get(0).getMeas2() == null) {
            binding.meas2.setVisibility(View.GONE);
        } else {
            binding.meas2.setText(foodMaterial.get(0).getMeas2());
        }
        if (foodMaterial.get(0).getMeas3().contentEquals("") || foodMaterial.get(0).getMeas3() == null) {
            binding.meas3.setVisibility(View.GONE);
        } else {
            binding.meas3.setText(foodMaterial.get(0).getMeas3());
        }
        if (foodMaterial.get(0).getMeas4().contentEquals("") || foodMaterial.get(0).getMeas4() == null) {
            binding.meas4.setVisibility(View.GONE);
        } else {
            binding.meas4.setText(foodMaterial.get(0).getMeas4());
        }
        if (foodMaterial.get(0).getMeas5().contentEquals("") || foodMaterial.get(0).getMeas5() == null) {
            binding.meas5.setVisibility(View.GONE);
        } else {
            binding.meas5.setText(foodMaterial.get(0).getMeas5());
        }
        if (foodMaterial.get(0).getMeas6().contentEquals("") || foodMaterial.get(0).getMeas6() == null) {
            binding.meas6.setVisibility(View.GONE);
        } else {
            binding.meas6.setText(foodMaterial.get(0).getMeas6());
        }
        if (foodMaterial.get(0).getMeas7().contentEquals("") || foodMaterial.get(0).getMeas7() == null) {
            binding.meas7.setVisibility(View.GONE);
        } else {
            binding.meas7.setText(foodMaterial.get(0).getMeas7());
        }
        if (foodMaterial.get(0).getMeas8().contentEquals("") || foodMaterial.get(0).getMeas8() == null) {
            binding.meas8.setVisibility(View.GONE);
        } else {
            binding.meas8.setText(foodMaterial.get(0).getMeas8());
        }
        if (foodMaterial.get(0).getMeas9().contentEquals("") || foodMaterial.get(0).getMeas9() == null) {
            binding.meas9.setVisibility(View.GONE);
        } else {
            binding.meas9.setText(foodMaterial.get(0).getMeas9());
        }
        if (foodMaterial.get(0).getMeas10().contentEquals("") || foodMaterial.get(0).getMeas10() == null) {
            binding.meas10.setVisibility(View.GONE);
        } else {
            binding.meas10.setText(foodMaterial.get(0).getMeas10());
        }
        if (foodMaterial.get(0).getMeas11().contentEquals("") || foodMaterial.get(0).getMeas11() == null) {
            binding.meas11.setVisibility(View.GONE);
        } else {
            binding.meas11.setText(foodMaterial.get(0).getMeas11());
        }
        if (foodMaterial.get(0).getMeas12().contentEquals("") || foodMaterial.get(0).getMeas12() == null) {
            binding.meas12.setVisibility(View.GONE);
        } else {
            binding.meas12.setText(foodMaterial.get(0).getMeas12());
        }
        if (foodMaterial.get(0).getMeas13().contentEquals("") || foodMaterial.get(0).getMeas13() == null) {
            binding.meas13.setVisibility(View.GONE);
        } else {
            binding.meas13.setText(foodMaterial.get(0).getMeas13());
        }
        if (foodMaterial.get(0).getMeas14().contentEquals("") || foodMaterial.get(0).getMeas14() == null) {
            binding.meas14.setVisibility(View.GONE);
        } else {
            binding.meas14.setText(foodMaterial.get(0).getMeas14());
        }
        if (foodMaterial.get(0).getMeas15().contentEquals("") || foodMaterial.get(0).getMeas15() == null) {
            binding.meas15.setVisibility(View.GONE);
        } else {
            binding.meas15.setText(foodMaterial.get(0).getMeas15());
        }
        if (foodMaterial.get(0).getMeas16().contentEquals("") || foodMaterial.get(0).getMeas16() == null) {
            binding.meas16.setVisibility(View.GONE);
        } else {
            binding.meas16.setText(foodMaterial.get(0).getMeas16());
        }
        if (foodMaterial.get(0).getMeas17().contentEquals("") || foodMaterial.get(0).getMeas17() == null) {
            binding.meas17.setVisibility(View.GONE);
        } else {
            binding.meas17.setText(foodMaterial.get(0).getMeas17());
        }
        if (foodMaterial.get(0).getMeas18().contentEquals("") || foodMaterial.get(0).getMeas18() == null) {
            binding.meas18.setVisibility(View.GONE);
        } else {
            binding.meas18.setText(foodMaterial.get(0).getMeas18());
        }
        if (foodMaterial.get(0).getMeas19().contentEquals("") || foodMaterial.get(0).getMeas19() == null) {
            binding.meas19.setVisibility(View.GONE);
        } else {
            binding.meas19.setText(foodMaterial.get(0).getMeas19());
        }
        if (foodMaterial.get(0).getMeas20().contentEquals("") || foodMaterial.get(0).getMeas20() == null) {
            binding.meas20.setVisibility(View.GONE);
        } else {
            binding.meas20.setText(foodMaterial.get(0).getMeas20());
        }
    }
}