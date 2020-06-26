package com.example.amafood.dailyfood;


import com.google.gson.annotations.SerializedName;

public class DailyFoodInfoDataClass {

    @SerializedName("strMealThumb")
    private String mealImg;

    @SerializedName("strMeal")
    private String mealName;

    @SerializedName("strTags")
    private String tag;

    @SerializedName("strArea")
    private String area;

    @SerializedName("strCategory")
    private String category;

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getTag() {
        return tag;
    }

    public String getMealImg() {
        return mealImg;
    }

    public String getMealName() {
        return mealName;
    }

}
