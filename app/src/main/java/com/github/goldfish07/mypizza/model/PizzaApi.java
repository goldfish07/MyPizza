package com.github.goldfish07.mypizza.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// Retrofit interface
public interface PizzaApi {

    String BASE_URL = "https://625bbd9d50128c570206e502.mockapi.io/api/v1/";

    /**
     * @return Pizza json response
     */
    @GET("/pizza")
    Call<List<Pizza>> getPizza();
}