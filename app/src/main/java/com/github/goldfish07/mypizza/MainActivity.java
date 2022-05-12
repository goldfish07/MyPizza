package com.github.goldfish07.mypizza;

import static com.github.goldfish07.mypizza.Constants.INTENT_KEY_MY_PIZZA_OBJ;
import static com.github.goldfish07.mypizza.Constants.REQUEST_CODE_CART_EMPTY;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.goldfish07.mypizza.activity.ViewCartActivity;
import com.github.goldfish07.mypizza.adapter.CrustAdapter;
import com.github.goldfish07.mypizza.adapter.PizzaAdapter;
import com.github.goldfish07.mypizza.adapter.SizeAdapter;
import com.github.goldfish07.mypizza.interfaces.OnCrustClickListener;
import com.github.goldfish07.mypizza.interfaces.OnAddPizzaListener;
import com.github.goldfish07.mypizza.interfaces.OnSizeClickListener;
import com.github.goldfish07.mypizza.interfaces.PizzaPriceListener;
import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.MyPizza;
import com.github.goldfish07.mypizza.model.Pizza;
import com.github.goldfish07.mypizza.model.Sizes;
import com.github.goldfish07.mypizza.ui.ProgressBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Pizza>>, OnAddPizzaListener, PizzaPriceListener, View.OnClickListener {

    private List<Pizza> pizzas;
    private PizzaAdapter pizzaAdapter;
    protected ProgressBar progressBar;
    private TextView txtViewCartPrice;
    public MyPizza myPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = new ProgressBar.Builder(this);
        setSupportActionBar(findViewById(R.id.toolbar));
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RelativeLayout viewCartBtn = findViewById(R.id.totalLayout);
        txtViewCartPrice = findViewById(R.id.totalPriceTxt);
        viewCartBtn.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pizzas = new ArrayList<>();
        pizzaAdapter = new PizzaAdapter(MainActivity.this, pizzas, this);
        recyclerView.setAdapter(pizzaAdapter);
        progressBar.show();
        RetrofitClient.getInstance().getApi().getPizza().enqueue(this);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResponse(@NonNull Call<List<Pizza>> call, Response<List<Pizza>> response) {
        List<Pizza> pizzasList = response.body();
        pizzas.addAll(pizzasList);
        pizzaAdapter.notifyDataSetChanged();
        progressBar.dismiss();
    }

    @Override
    public void onFailure(@NonNull Call<List<Pizza>> call, @NonNull Throwable t) {
        progressBar.dismiss();
    }

    @Override
    public void onAddPizza(Pizza pizza) {
//        int price;
////        if (txtPrice.getText().toString().isEmpty()) {
//            price = pizza.getCrusts().get(pizza.getDefaultCrust()).getSizes().get(pizza.getDefaultCrust()).getPrice();
////        } else {
////            price = Integer.parseInt(txtPrice.getText().toString()) + Integer.parseInt(txtPrice.getText().toString());
////        }
//        txtPrice.setText(String.valueOf(price));
        new CustomizeDialog(this, pizza, this).show(getSupportFragmentManager(), "this");
    }

    int price = 0;
    ArrayList<MyPizza> myPizzaArrayList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    public void onPriceUpdate(MyPizza myPizza, int totalPrice) {
        if (price == 0) {
            price = totalPrice;
        } else {
            price = price + totalPrice;
        }
        if(myPizzaArrayList.size()<2){
            myPizzaArrayList.add(myPizza);
            txtViewCartPrice.setText(Integer.toString(price));
        } else {
            Toast.makeText(this,"you can order only 2 different pizzas",Toast.LENGTH_LONG).show();
        }
        Log.e("price", String.valueOf(price));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.totalLayout) {
            Intent intent = new Intent(MainActivity.this, ViewCartActivity.class);
            if (myPizza != null)
                myPizzaArrayList.add(myPizza);
            intent.putParcelableArrayListExtra(INTENT_KEY_MY_PIZZA_OBJ, myPizzaArrayList);
            launcher.launch(intent);
        }
    }


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == REQUEST_CODE_CART_EMPTY) {
                    myPizzaArrayList.clear();
                    txtViewCartPrice.setText("0");
                }
            });


    public static class CustomizeDialog extends BottomSheetDialogFragment implements OnCrustClickListener, OnSizeClickListener, View.OnClickListener {

        private final Activity context;
        private RecyclerView crustRecyclerView;
        private RecyclerView sizesRecyclerView;
        TextView pizzaName;
        TextView pizzaDesc;
        TextView sizePriceTxt;

        ImageView foodTypeIndicator;

        Pizza pizza;
        List<Crusts> crusts;
        PizzaPriceListener pizzaPriceListener;
        MaterialButton addItemToCartBtn;
        //        Sizes sizes = null;
        MyPizza myPizza;

        public CustomizeDialog(Activity context, Pizza pizza, PizzaPriceListener pizzaPriceListener) {
            this.context = context;
            this.pizza = pizza;
            this.pizzaPriceListener = pizzaPriceListener;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = getLayoutInflater().inflate(R.layout.dialog_customize, container, false);
            crustRecyclerView = view.findViewById(R.id.recyclerView);
            pizzaName = view.findViewById(R.id.pizzaName);
            pizzaDesc = view.findViewById(R.id.pizzaDesc);
            sizePriceTxt = view.findViewById(R.id.sizePriceTxt);
            foodTypeIndicator = view.findViewById(R.id.foodTypeIndicator);
            crustRecyclerView = view.findViewById(R.id.crust_recyclerView);
            sizesRecyclerView = view.findViewById(R.id.size_recyclerView);
            addItemToCartBtn = view.findViewById(R.id.addItemToCartBtn);

            addItemToCartBtn.setOnClickListener(this);
            crusts = pizza.getCrusts();

//        Log.e("pizzaName", String.valueOf(crusts.size()));
            pizzaName.setText(pizza.getName());
            pizzaDesc.setText(pizza.getDescription());
            foodTypeIndicator.setImageResource(pizza.getVeg() ? R.drawable.ic_veg : R.drawable.ic_non_veg);
            return view;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            crustRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            sizesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            crustRecyclerView.setAdapter(new CrustAdapter(context, pizza, this));
        }

        @Override
        public void onCrustSelected(Pizza pizza, Crusts crusts) {
            sizesRecyclerView.setAdapter(new SizeAdapter(context, pizza, crusts, this));
        }

        @Override
        public void onSizeSelected(Sizes sizes) {
            // this.sizes = sizes;
            sizePriceTxt.setText(String.valueOf(sizes.getPrice()));
        }

        @Override
        public void onSizeSelected(MyPizza myPizza) {
            this.myPizza = myPizza;
            sizePriceTxt.setText(String.valueOf(myPizza.getPrice()));
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.addItemToCartBtn) {
                if (myPizza != null)
                    pizzaPriceListener.onPriceUpdate(myPizza, myPizza.getPrice());
                dismiss();
            }
        }
    }

}