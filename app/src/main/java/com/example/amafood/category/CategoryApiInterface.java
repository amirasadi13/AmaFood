package com.example.amafood.category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryApiInterface {

    @GET("categories.php")
    Call<List<CategoryListDataClass>>
    getCategoryList();

}
