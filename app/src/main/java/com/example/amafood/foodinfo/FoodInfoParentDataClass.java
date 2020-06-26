package com.example.amafood.foodinfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodInfoParentDataClass {

    @SerializedName("meals")
    private List<FoodMaterialDataClass> foodMaterial;

    public List<FoodMaterialDataClass> getFoodMaterial() {
        return foodMaterial;
    }
}
