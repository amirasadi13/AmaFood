package com.example.amafood.category;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryListItemsDataClass implements Serializable {

    @SerializedName("strCategory")
    private String title;

    @SerializedName("strCategoryThumb")
    private String img;

    @SerializedName("strCategoryDescription")
    private String description;

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }
}
