package com.example.amafood.category;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRetrofitClient {

    private static Retrofit retrofit ;
    public static String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public static Retrofit getRetrofit(){

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
