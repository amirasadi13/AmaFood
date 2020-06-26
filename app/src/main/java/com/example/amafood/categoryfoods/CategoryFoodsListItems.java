package com.example.amafood.categoryfoods;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.amafood.R;
import com.example.amafood.category.CategoryRetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFoodsListItems extends Fragment {

    String title;
    RecyclerView recyclerView;
    FrameLayout progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_foods_list_items, container, false);
        title = getArguments().getString("title");
        recyclerView = view.findViewById(R.id.rv_foods_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar = view.findViewById(R.id.loading_foods_list);

        getData();
        return view;
    }

    private void getData() {
        FoodsListApiService foodsListApiService = CategoryRetrofitClient.getRetrofit().create(FoodsListApiService.class);
        Call<CategoryFoodsListDataClass> call = foodsListApiService.getCategoryFoodsList(title);
        call.enqueue(new Callback<CategoryFoodsListDataClass>() {
            @Override
            public void onResponse(Call<CategoryFoodsListDataClass> call, Response<CategoryFoodsListDataClass> response) {
                initRecycle(response.body());
                if (response.body() != null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CategoryFoodsListDataClass> call, Throwable t) {

            }
        });
    }

    private void initRecycle(CategoryFoodsListDataClass categoryFoodsListDataClass) {
        CategoryFoodsListRecycleAdapter categoryFoodsListRecycleAdapter = new CategoryFoodsListRecycleAdapter(categoryFoodsListDataClass.getMeals(), getContext());
        recyclerView.setAdapter(categoryFoodsListRecycleAdapter);
    }

}