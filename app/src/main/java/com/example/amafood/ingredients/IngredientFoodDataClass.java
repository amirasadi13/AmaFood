package com.example.amafood.ingredients;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientFoodDataClass {


    @SerializedName("meals")
    private List<IngredientFoodsListDataClass> foodsList;

    public List<IngredientFoodsListDataClass> getFoodsList() {
        return foodsList;
    }
}
