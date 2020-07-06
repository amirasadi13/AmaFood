package com.example.amafood.category;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amafood.R;
import com.example.amafood.databinding.FragmentCategoryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;
    String date;
    boolean chooseFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);

        getChooseFoodOfCalender();
        setBackArrow();
        setRecycler();
        getData();
        return binding.getRoot();
    }

    private void getChooseFoodOfCalender() {
        if (getArguments().getString("date") != null) {
            date = getArguments().getString("date");
            chooseFood = getArguments().getBoolean("chooseFood");
        }else {
            date = null;
            chooseFood = false;
        }
    }

    private void setBackArrow() {

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar7);
        binding.toolbar7.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        binding.toolbar7.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_categoryFragment_to_homePageFragment);
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
                    binding.progressBar.setVisibility(View.GONE);
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
        CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(getContext(),
                categoryListDataClass.getCategoriesItems(),chooseFood,date);
        binding.rvCategory.setAdapter(categoryRecyclerViewAdapter);
    }

    private void setRecycler() {

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvCategory.setLayoutManager(linearLayoutManager);

    }
}