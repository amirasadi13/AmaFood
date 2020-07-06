package com.example.amafood;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
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
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.example.amafood.calender.food.datebase.CalenderFoodDataBase;
import com.example.amafood.calender.food.datebase.CalenderFoodDeo;
import com.example.amafood.calender.food.datebase.CalenderFoods;
import com.example.amafood.category.CategoryApiInterface;
import com.example.amafood.category.CategoryListDataClass;
import com.example.amafood.category.CategoryRecyclerViewAdapter;
import com.example.amafood.category.CategoryRetrofitClient;
import com.example.amafood.databinding.FragmentHomePageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment {

    FragmentHomePageBinding binding;
    boolean chooseFood = true;
    CalenderFoodDeo calenderFoodDeo;
    List<CalenderFoods> foodsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false);

        binding.calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String day = String.valueOf(dayOfMonth);
                String curMonth = String.valueOf(month);
                String curDate = month + day;
                binding.fabAddFood.setVisibility(View.VISIBLE);
                setCalenderFoodsRecycle(curDate);
                setFabNavigate(curDate);
            }
        });

        setNavigationDrawerLayout();
        return binding.getRoot();
    }

    private void setCalenderFoodsRecycle(String curDate) {


        calenderFoodDeo = CalenderFoodDataBase.getInstance(getContext()).calenderFoodDeo();
        foodsList = calenderFoodDeo.getNoteList(curDate);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.rvCalenderFoods.setLayoutManager(linearLayoutManager);

        CalenderFoodsRecycleAdapter calenderFoodsRecycleAdapter = new CalenderFoodsRecycleAdapter(foodsList,getContext());
        binding.rvCalenderFoods.setAdapter(calenderFoodsRecycleAdapter);
    }

    private void setFabNavigate(final String curDate) {
        binding.fabAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("date", curDate);
                bundle.putBoolean("chooseFood", chooseFood);
                Navigation.findNavController(v).navigate(R.id.action_homePageFragment_to_categoryFragment2, bundle);
            }
        });
    }


    private void setNavigationDrawerLayout() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), binding.drawerLayout, binding
                .toolbar, 0, 0);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
                    case R.id.btn_go_to_category:
                        Bundle bundle = new Bundle();
                        bundle.putString("date", null);
                        bundle.putBoolean("chooseFood", false);
                        Navigation.findNavController(getView()).navigate(R.id.action_homePageFragment_to_categoryFragment2, bundle);
                        break;
                }
                return false;
            }
        });
    }

}