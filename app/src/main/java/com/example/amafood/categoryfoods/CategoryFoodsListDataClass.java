package com.example.amafood.categoryfoods;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryFoodsListDataClass {

    @SerializedName("meals")
    private List<CategoryFoodsItemsListDataClass> meals;

    public List<CategoryFoodsItemsListDataClass> getMeals() {
        return meals;
    }
}
