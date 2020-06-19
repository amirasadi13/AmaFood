package com.example.amafood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amafood.category.CategoryApiInterface;
import com.example.amafood.category.CategoryListDataClass;
import com.example.amafood.category.CategoryRetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment {

    List<CategoryListDataClass> categoryList;
    CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        categoryList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(categoryList, getContext());
        recyclerView.setAdapter(categoryRecyclerViewAdapter);


        getData();
        return view;
    }

    private void getData() {
        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<List<CategoryListDataClass>> call = categoryApiInterface.getCategoryList();
        call.enqueue(new Callback<List<CategoryListDataClass>>() {
            @Override
            public void onResponse(Call<List<CategoryListDataClass>> call, Response<List<CategoryListDataClass>> response) {
                Toast.makeText(getContext(), "Connected", Toast.LENGTH_SHORT).show();
                categoryList = response.body();
                categoryRecyclerViewAdapter.setCategoryList(categoryList);

            }

            @Override
            public void onFailure(Call<List<CategoryListDataClass>> call, Throwable t) {
                Log.e("tag","onFailure",t);
                Toast.makeText(getContext(), "not connected"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}