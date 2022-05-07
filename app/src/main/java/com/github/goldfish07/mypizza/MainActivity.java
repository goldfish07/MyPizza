package com.github.goldfish07.mypizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.goldfish07.mypizza.adapter.PizzaAdapter;
import com.github.goldfish07.mypizza.model.Pizza;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchPizzas();
    }


    private void fetchPizzas() {
        Call<List<Pizza>> call = RetrofitClient.getInstance().getMyApi().getPizza();
        call.enqueue(new Callback<List<Pizza>>() {
            @Override
            public void onResponse(@NonNull Call<List<Pizza>> call, @NonNull Response<List<Pizza>> response) {
                List<Pizza> pizzas = response.body();
                recyclerView.setAdapter(new PizzaAdapter(MainActivity.this, pizzas));
            }

            @Override
            public void onFailure(@NonNull Call<List<Pizza>> call, @NonNull Throwable t) {

            }

        });
    }


}