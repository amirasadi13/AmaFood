package com.example.amafood.countriesfoods;

import com.google.gson.annotations.SerializedName;

public class CountryListItemsDataClass {

    @SerializedName("strArea")
    private String area;

    public String getArea() {
        return area;
    }
}
