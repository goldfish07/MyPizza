package com.github.goldfish07.mypizza;

import com.github.goldfish07.mypizza.model.PizzaApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private final PizzaApi api;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PizzaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(PizzaApi.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public PizzaApi getApi() {
        return api;
    }
}