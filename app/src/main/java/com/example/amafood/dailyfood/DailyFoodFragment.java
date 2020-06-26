package com.example.amafood.dailyfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyFoodFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    Button btnFoodInfo;
    ProgressBar loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_daily_food, container, false);
        btnFoodInfo = view.findViewById(R.id.btn_check_daily_food_info);
        loading = view.findViewById(R.id.daily_loading);

        setRecycle();
        btnFoodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDailyFood();
                recyclerView.setVisibility(View.VISIBLE);
                btnFoodInfo.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    private void setRecycle() {
        recyclerView = view.findViewById(R.id.rv_daily_food);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getDailyFood() {
        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<DailyFoodDataClass> call = categoryApiInterface.getDailyFoodsList();
        call.enqueue(new Callback<DailyFoodDataClass>() {
            @Override
            public void onResponse(Call<DailyFoodDataClass> call, Response<DailyFoodDataClass> response) {
                initList(response.body());
                if (response.body() != null){
                    loading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DailyFoodDataClass> call, Throwable t) {

            }
        });
    }

    private void initList(DailyFoodDataClass dailyFoodDataClass) {
        DailyFoodRecycleAdapter dailyFoodRecycleAdapter = new DailyFoodRecycleAdapter(dailyFoodDataClass.getFoodInfo(), getContext());
        recyclerView.setAdapter(dailyFoodRecycleAdapter);
    }
}