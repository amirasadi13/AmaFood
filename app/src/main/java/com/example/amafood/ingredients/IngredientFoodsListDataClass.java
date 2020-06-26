package com.example.amafood.ingredients;

import com.google.gson.annotations.SerializedName;

public class IngredientFoodsListDataClass {

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
