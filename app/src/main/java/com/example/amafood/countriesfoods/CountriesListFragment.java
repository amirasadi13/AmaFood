package com.example.amafood.countriesfoods;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class CountriesListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView;
    View view;
    Spinner spinner;
    List<CountryListItemsDataClass> countryListItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_countries_list, container, false);
        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        countryListItems = new ArrayList<>();
        setRecycleLayout();
        getCountriesList();
        return view;
    }


    private void setRecycleLayout() {
        recyclerView = view.findViewById(R.id.rv_countries);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void getCountriesList() {

        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<CountryListDataClass> call = categoryApiInterface.getCountryList("list");
        call.enqueue(new Callback<CountryListDataClass>() {
            @Override
            public void onResponse(Call<CountryListDataClass> call, Response<CountryListDataClass> response) {
                initRecycle(response.body());
                if (response.body() != null) {
                    spinner.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CountryListDataClass> call, Throwable t) {

            }
        });

    }

    private void initRecycle(CountryListDataClass countryListDataClass) {
        countryListItems = countryListDataClass.getCountryList();
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(countryListItems, getContext());
        spinner.setAdapter(customSpinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String countryName = countryListItems.get(position).getArea();
        getFoodsList(countryName);
    }

    private void getFoodsList(String countryName) {
        CategoryApiInterface categoryApiInterface = CategoryRetrofitClient.getRetrofit().create(CategoryApiInterface.class);
        Call<CountryFoodsDataClass> call = categoryApiInterface.getCountryFoodsList(countryName);
        call.enqueue(new Callback<CountryFoodsDataClass>() {
            @Override
            public void onResponse(Call<CountryFoodsDataClass> call, Response<CountryFoodsDataClass> response) {
                initCountyFoodsRecycle(response.body());
            }

            @Override
            public void onFailure(Call<CountryFoodsDataClass> call, Throwable t) {

            }
        });
    }
    private void initCountyFoodsRecycle(CountryFoodsDataClass countryFoodsDataClass){
        CountriesListRecycleAdapter countriesListRecycleAdapter = new CountriesListRecycleAdapter(countryFoodsDataClass.getCountryFoodsList(),getContext());
        recyclerView.setAdapter(countriesListRecycleAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}