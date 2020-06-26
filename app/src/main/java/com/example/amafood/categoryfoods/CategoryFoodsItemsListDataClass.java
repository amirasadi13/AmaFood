package com.example.amafood.categoryfoods;

import com.google.gson.annotations.SerializedName;

public class CategoryFoodsItemsListDataClass {

    @SerializedName("strMeal")
    private String mealName;

    @SerializedName("strMealThumb")
    private String img;

    public String getImg() {
        return img;
    }

    public String getMealName() {
        return mealName;
    }
}
