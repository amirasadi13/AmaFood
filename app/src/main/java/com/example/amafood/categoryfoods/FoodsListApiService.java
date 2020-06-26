package com.example.amafood.categoryfoods;

import com.example.amafood.category.CategoryListDataClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodsListApiService {

    @GET("api/json/v1/1/filter.php")
    Call<CategoryFoodsListDataClass>
    getCategoryFoodsList(@Query("c") String category);
}
