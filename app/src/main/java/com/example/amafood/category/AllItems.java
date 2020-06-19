package com.example.amafood.category;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AllItems implements Serializable {

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }
}
