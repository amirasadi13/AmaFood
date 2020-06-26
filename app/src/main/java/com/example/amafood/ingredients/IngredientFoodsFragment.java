package com.example.amafood.ingredients;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amafood.R;
import com.example.amafood.category.CategoryApiInterface;
import com.example.amafood.category.CategoryRetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientFoodsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View view;
    Spinner spinner;
    List<IngredientListItemsDataClass> ingredientListItems;
    LinearLayout spinnerLayout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ingredient_foods, container, false);
        setRecycle();
        setSpinner();
        getIngredientList();
        return view;
    }

    private void setRecycle() {
        recyclerView = view.findViewById(R.id.rv_ingredient_foods);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void setSpinner() {
        spinner = view.findViewById(R.id.sp_ingredients_list);
        spinnerLayout = view.findViewById(R.id.spinner_layout_ing);
        spinner.setOnItemSelectedListener(this);
        ingredientListItems = new ArrayList<>();
    }

    private void getIngredientList() {
        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<IngredientDataClass> call = categoryApiInterface.getIngredientsList("list");
        call.enqueue(new Callback<IngredientDataClass>() {
            @Override
            public void onResponse(Call<IngredientDataClass> call, Response<IngredientDataClass> response) {
                initSpinner(response.body());
                if (response.body() != null) {
                    spinnerLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<IngredientDataClass> call, Throwable t) {

            }
        });

    }

    private void initSpinner(IngredientDataClass ingredientDataClass) {
        ingredientListItems = ingredientDataClass.getIngredientList();
        IngredientSpinnerAdapter ingredientSpinnerAdapter = new IngredientSpinnerAdapter(ingredientListItems, getContext());
        spinner.setAdapter(ingredientSpinnerAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String ingredientName = ingredientListItems.get(position).getIngredientName();
        getIngredientFoodsList(ingredientName);
    }

    private void getIngredientFoodsList(String ingredientName) {
        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<IngredientFoodDataClass> call = categoryApiInterface.getIngredientsFoodsList(ingredientName);
        call.enqueue(new Callback<IngredientFoodDataClass>() {
            @Override
            public void onResponse(Call<IngredientFoodDataClass> call, Response<IngredientFoodDataClass> response) {
                initRecycle(response.body());
            }

            @Override
            public void onFailure(Call<IngredientFoodDataClass> call, Throwable t) {

            }
        });
    }

    private void initRecycle(IngredientFoodDataClass ingredientFoodDataClass) {
        IngredientFoodsRecycleAdapter ingredientFoodsRecycleAdapter = new IngredientFoodsRecycleAdapter(ingredientFoodDataClass.getFoodsList(), getContext());
        recyclerView.setAdapter(ingredientFoodsRecycleAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}