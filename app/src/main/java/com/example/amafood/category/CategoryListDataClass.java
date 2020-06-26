package com.example.amafood.category;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryListDataClass implements Serializable {

    @SerializedName("categories")
    private List<CategoryListItemsDataClass> categoriesItems;

    public List<CategoryListItemsDataClass> getCategoriesItems() {
        return categoriesItems;
    }
}
