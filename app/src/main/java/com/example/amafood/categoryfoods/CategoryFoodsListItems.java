package com.example.amafood.categoryfoods;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.amafood.R;
import com.example.amafood.category.CategoryRetrofitClient;
import com.example.amafood.databinding.FragmentCategoryFoodsListItemsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFoodsListItems extends Fragment {

    String title;
    FragmentCategoryFoodsListItemsBinding binding;
    String date;
    boolean chooseFood;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_foods_list_items, container, false);

        getChooseFoodOfCalender();
        setBackArrow();
        setRecycleView();
        getData();
        setToolbarTitle();
        return binding.getRoot();
    }

    private void setToolbarTitle() {
        binding.toolbar4.setTitle(title);
    }

    private void getChooseFoodOfCalender() {
        chooseFood = getArguments().getBoolean("chooseFood");
        date = getArguments().getString("date");
    }

    private void setBackArrow() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar4);
        binding.toolbar4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.toolbar4.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
                Navigation.findNavController(v).navigate(R.id.action_categoryFoodsListItems_to_categoryFragment);
            }
        });
    }


    private void setRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvFoodsList.setLayoutManager(linearLayoutManager);
    }

    private void getData() {
        title = getArguments().getString("title");
        FoodsListApiService foodsListApiService = CategoryRetrofitClient.getRetrofit().create(FoodsListApiService.class);
        Call<CategoryFoodsListDataClass> call = foodsListApiService.getCategoryFoodsList(title);
        call.enqueue(new Callback<CategoryFoodsListDataClass>() {
            @Override
            public void onResponse(Call<CategoryFoodsListDataClass> call, Response<CategoryFoodsListDataClass> response) {
                initRecycle(response.body());
                if (response.body() != null) {
                    binding.loadingFoodsList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CategoryFoodsListDataClass> call, Throwable t) {

            }
        });
    }

    private void initRecycle(CategoryFoodsListDataClass categoryFoodsListDataClass) {
        CategoryFoodsListRecycleAdapter categoryFoodsListRecycleAdapter = new CategoryFoodsListRecycleAdapter(categoryFoodsListDataClass.getMeals(), getContext()
        ,chooseFood,date);
        binding.rvFoodsList.setAdapter(categoryFoodsListRecycleAdapter);
    }

}