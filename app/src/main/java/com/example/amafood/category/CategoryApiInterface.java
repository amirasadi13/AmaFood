package com.example.amafood.category;

import com.example.amafood.countriesfoods.CountryFoodsDataClass;
import com.example.amafood.countriesfoods.CountryListDataClass;
import com.example.amafood.dailyfood.DailyFoodDataClass;
import com.example.amafood.ingredients.IngredientDataClass;
import com.example.amafood.ingredients.IngredientFoodDataClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryApiInterface {

    @GET("api/json/v1/1/categories.php")
    Call<CategoryListDataClass>
    getCategoryList();

    @GET("api/json/v1/1/list.php")
    Call<CountryListDataClass>
    getCountryList(@Query("a") String countryFilter);

    @GET("api/json/v1/1/filter.php")
    Call<CountryFoodsDataClass>
    getCountryFoodsList(@Query("a") String countryName);

    @GET("api/json/v1/1/list.php")
    Call<IngredientDataClass>
    getIngredientsList(@Query("i") String ingredient);

    @GET("api/json/v1/1/filter.php")
    Call<IngredientFoodDataClass>
    getIngredientsFoodsList(@Query("i") String ingredientName);

    @GET("api/json/v1/1/random.php")
    Call<DailyFoodDataClass>
    getDailyFoodsList();
}
