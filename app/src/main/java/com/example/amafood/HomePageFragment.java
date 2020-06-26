package com.example.amafood;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.example.amafood.category.CategoryApiInterface;
import com.example.amafood.category.CategoryListDataClass;
import com.example.amafood.category.CategoryRecyclerViewAdapter;
import com.example.amafood.category.CategoryRetrofitClient;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment {

    RecyclerView recyclerView;
    FrameLayout progressBar;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView = view.findViewById(R.id.rv_category);
        progressBar = view.findViewById(R.id.loading_category);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(linearLayoutManager);

        setNavigationDrawerLayout(view);
        getData();
        return view;
    }

    private void setNavigationDrawerLayout(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.go_to_countries_foods:
                        Navigation.findNavController(getView()).navigate(R.id.action_homePageFragment_to_countriesListFragment);
                        break;
                    case R.id.go_to_search_by_ingredient_page:
                        Navigation.findNavController(getView()).navigate(R.id.action_homePageFragment_to_ingredientFoodsFragment);
                        break;
                    case R.id.go_to_random_food_page:
                        Navigation.findNavController(getView()).navigate(R.id.action_homePageFragment_to_dailyFoodFragment);
                        break;
                }
                return false;
            }
        });
    }

    private void getData() {
        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<CategoryListDataClass> call = categoryApiInterface.getCategoryList();
        call.enqueue(new Callback<CategoryListDataClass>() {
            @Override
            public void onResponse(Call<CategoryListDataClass> call, Response<CategoryListDataClass> response) {
                initRecycleView(response.body());
                if (response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CategoryListDataClass> call, Throwable t) {
                Log.e("tag", "onFailure", t);
                Toast.makeText(getContext(), "not connected" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecycleView(CategoryListDataClass categoryListDataClass) {
        CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(getContext(), categoryListDataClass.getCategoriesItems());
        recyclerView.setAdapter(categoryRecyclerViewAdapter);
    }
}