package com.example.amafood.countriesfoods;

import com.google.gson.annotations.SerializedName;

public class CountryFoodsListItemsDataClass {

    @SerializedName("strMeal")
    private String mealName;

    @SerializedName("strMealThumb")
    private String mealImg;

    public String getMealImg() {
        return mealImg;
    }

    public String getMealName() {
        return mealName;
    }
}
