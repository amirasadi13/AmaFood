package com.example.amafood.ingredients;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientDataClass {

    @SerializedName("meals")
    private List<IngredientListItemsDataClass> ingredientList;

    public List<IngredientListItemsDataClass> getIngredientList() {
        return ingredientList;
    }
}
