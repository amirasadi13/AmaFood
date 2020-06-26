package com.example.amafood.dailyfood;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyFoodDataClass {

    @SerializedName("meals")
    private List<DailyFoodInfoDataClass> foodInfo;

    public List<DailyFoodInfoDataClass> getFoodInfo() {
        return foodInfo;
    }
}
