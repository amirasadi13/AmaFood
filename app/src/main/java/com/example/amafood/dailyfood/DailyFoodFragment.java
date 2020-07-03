package com.example.amafood.dailyfood;

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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.amafood.R;
import com.example.amafood.category.CategoryApiInterface;
import com.example.amafood.category.CategoryRetrofitClient;
import com.example.amafood.databinding.FragmentDailyFoodBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyFoodFragment extends Fragment {

    FragmentDailyFoodBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_daily_food, container, false);



        setBackArrow();
        setRecycle();
        binding.btnCheckDailyFoodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDailyFood();
                binding.rvDailyFood.setVisibility(View.VISIBLE);
                binding.btnCheckDailyFoodInfo.setVisibility(View.GONE);
                binding.dailyLoading.setVisibility(View.VISIBLE);
            }
        });



        return binding.getRoot();
    }

    private void setBackArrow() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar6);
        binding.toolbar6.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        binding.toolbar6.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dailyFoodFragment_to_homePageFragment);
            }
        });

    }

    private void setRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvDailyFood.setLayoutManager(linearLayoutManager);
    }

    private void getDailyFood() {
        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<DailyFoodDataClass> call = categoryApiInterface.getDailyFoodsList();
        call.enqueue(new Callback<DailyFoodDataClass>() {
            @Override
            public void onResponse(Call<DailyFoodDataClass> call, Response<DailyFoodDataClass> response) {
                initList(response.body());
                if (response.body() != null) {
                    binding.dailyLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DailyFoodDataClass> call, Throwable t) {

            }
        });
    }

    private void initList(DailyFoodDataClass dailyFoodDataClass) {
        DailyFoodRecycleAdapter dailyFoodRecycleAdapter = new DailyFoodRecycleAdapter(dailyFoodDataClass.getFoodInfo(), getContext());
        binding.rvDailyFood.setAdapter(dailyFoodRecycleAdapter);
    }
}