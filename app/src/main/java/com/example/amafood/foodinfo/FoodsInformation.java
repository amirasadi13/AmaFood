package com.example.amafood.foodinfo;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.amafood.R;
import com.example.amafood.category.CategoryRetrofitClient;
import com.example.amafood.databinding.FragmentFoodsInformationBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsInformation extends Fragment {
    FragmentFoodsInformationBinding binding;
    String mealName;
    FoodInfoParentDataClass foodInfo;
    List<FoodMaterialDataClass> foodMaterial;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_foods_information, container, false);
        getData();
        setData();
        return binding.getRoot();
    }

    private void setData() {
        binding.tvMealName.setText(foodMaterial.get(0).getMealName());
        binding.foodInstruction.setText(foodMaterial.get(0).getMealInstruction());
        binding.tvArea.setText(foodMaterial.get(0).getMealArea());
        binding.tvCategory.setText(foodMaterial.get(0).getMealCategory());
        Glide.with(getContext()).load(foodMaterial.get(0).getMealImg()).into(binding.imgFoodMaterial);
    }

    private void getData() {
        mealName = getArguments().getString("mealName");
        FoodInfoApiService foodInfoApiService = CategoryRetrofitClient.getRetrofit().create(FoodInfoApiService.class);
        Call<FoodInfoParentDataClass> call = foodInfoApiService.getFoodsInfo(mealName);
        call.enqueue(new Callback<FoodInfoParentDataClass>() {
            @Override
            public void onResponse(Call<FoodInfoParentDataClass> call, Response<FoodInfoParentDataClass> response) {
                foodInfo = response.body();
                foodMaterial = foodInfo.getFoodMaterial();
            }

            @Override
            public void onFailure(Call<FoodInfoParentDataClass> call, Throwable t) {

            }
        });
    }
}