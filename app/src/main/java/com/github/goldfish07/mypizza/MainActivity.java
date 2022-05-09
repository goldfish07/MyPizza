package com.github.goldfish07.mypizza;

import static com.github.goldfish07.mypizza.Constants.INTENT_KEY_MY_PIZZA_OBJ;

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

import com.github.goldfish07.mypizza.activity.ViewCartActivity;
import com.github.goldfish07.mypizza.adapter.CrustAdapter;
import com.github.goldfish07.mypizza.adapter.PizzaAdapter;
import com.github.goldfish07.mypizza.adapter.SizeAdapter;
import com.github.goldfish07.mypizza.interfaces.MyPizzaListener;
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

public class MainActivity extends AppCompatActivity implements Callback<List<Pizza>>, OnAddPizzaListener, PizzaPriceListener, View.OnClickListener, MyPizzaListener {

    RecyclerView recyclerView;
    List<Pizza> pizzas;
    PizzaAdapter pizzaAdapter;
    protected ProgressBar progressBar;
    TextView txtPrice;
    RelativeLayout viewCartBtn;
    public MyPizza myPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = new ProgressBar.Builder(this);
        setSupportActionBar(findViewById(R.id.toolbar));
        recyclerView = findViewById(R.id.recyclerView);
        viewCartBtn = findViewById(R.id.materialButton);
        txtPrice = findViewById(R.id.txtPrice);
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
        new CustomizeDialog(this, pizza, this, this).show(getSupportFragmentManager(), "this");
    }

    int price = 0;

    @Override
    public void onPriceUpdate(int totalPrice) {
//        if (price == 0) {
//            price = totalPrice;
//        } else {
//            price = price + totalPrice;
//        }
        txtPrice.setText(String.valueOf(totalPrice));
        Log.e("price", String.valueOf(price));
    }

    ArrayList<MyPizza> myPizzaArrayList = new ArrayList<>();

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.materialButton) {
            Intent intent = new Intent(MainActivity.this, ViewCartActivity.class);
            if (myPizza != null)
                myPizzaArrayList.add(myPizza);
                intent.putParcelableArrayListExtra(INTENT_KEY_MY_PIZZA_OBJ, myPizzaArrayList);
            startActivity(intent);
        }
    }

    @Override
    public void onMyPizza(MyPizza myPizza) {
        this.myPizza = myPizza;
    }

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
        MyPizzaListener myPizzaListener;
        MaterialButton addItemToCartBtn;
//        Sizes sizes = null;
        MyPizza myPizza;

        public CustomizeDialog(Activity context, Pizza pizza, PizzaPriceListener pizzaPriceListener, MyPizzaListener myPizzaListener) {
            this.context = context;
            this.pizza = pizza;
            this.pizzaPriceListener = pizzaPriceListener;
            this.myPizzaListener = myPizzaListener;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //  context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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
            this.myPizza=myPizza;
            myPizzaListener.onMyPizza(myPizza);
            sizePriceTxt.setText(String.valueOf(myPizza.getPrice()));

        }


        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.addItemToCartBtn) {
                if (myPizza != null)
                    pizzaPriceListener.onPriceUpdate(myPizza.getPrice());
                dismiss();
            }
        }
    }

}