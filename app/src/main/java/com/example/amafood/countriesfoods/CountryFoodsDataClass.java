package com.example.amafood.countriesfoods;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryFoodsDataClass {

    @SerializedName("meals")
    private List<CountryFoodsListItemsDataClass> CountryFoodsList;

    public List<CountryFoodsListItemsDataClass> getCountryFoodsList() {
        return CountryFoodsList;
    }
}
