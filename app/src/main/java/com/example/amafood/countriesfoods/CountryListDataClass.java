package com.example.amafood.countriesfoods;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryListDataClass {

    @SerializedName("meals")
    private List<CountryListItemsDataClass> countryList;

    public List<CountryListItemsDataClass> getCountryList() {
        return countryList;
    }
}
