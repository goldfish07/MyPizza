package com.github.goldfish07.mypizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.goldfish07.mypizza.model.Pizza;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPizzas();
    }


    private void getPizzas() {
        Call<List<Pizza>> call = RetrofitClient.getInstance().getMyApi().getPizza();
        call.enqueue(new Callback<List<Pizza>>() {
            @Override
            public void onResponse(@NonNull Call<List<Pizza>> call, @NonNull Response<List<Pizza>> response) {
                List<Pizza> pizzas = response.body();
                Log.e("toString", pizzas.get(0).toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<Pizza>> call, @NonNull Throwable t) {

            }

        });
    }


}