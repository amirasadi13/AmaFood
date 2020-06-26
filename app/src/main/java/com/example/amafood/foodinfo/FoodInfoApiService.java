package com.example.amafood.foodinfo;

import com.example.amafood.categoryfoods.CategoryFoodsListDataClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodInfoApiService {

    @GET("api/json/v1/1/search.php")
    Call<FoodInfoParentDataClass>
    getFoodsInfo(@Query("s") String category);

}
