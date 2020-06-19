package com.example.amafood.category;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryListDataClass implements Serializable {

    @SerializedName("categories")
    private List<AllItems> categoriesItems;

    public List<AllItems> getCategoriesItems() {
        return categoriesItems;
    }
}
