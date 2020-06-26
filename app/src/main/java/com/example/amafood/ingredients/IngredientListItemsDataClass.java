package com.example.amafood.ingredients;

import com.google.gson.annotations.SerializedName;

public class IngredientListItemsDataClass {

    @SerializedName("strIngredient")
    private String ingredientName;

    @SerializedName("strType")
    private String ingType;

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngType() {
        return ingType;
    }
}
