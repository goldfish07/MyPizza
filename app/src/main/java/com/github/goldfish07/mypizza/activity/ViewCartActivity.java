package com.github.goldfish07.mypizza.activity;

import static com.github.goldfish07.mypizza.Constants.INTENT_KEY_MY_PIZZA_OBJ;
import static com.github.goldfish07.mypizza.Constants.REQUEST_CODE_CART_EMPTY;
import static com.github.goldfish07.mypizza.Constants.REQUEST_CODE_ORDER_PLACED;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.goldfish07.mypizza.MainActivity;
import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.Utils;
import com.github.goldfish07.mypizza.adapter.MyPizzaAdapter;
import com.github.goldfish07.mypizza.interfaces.OnCartUpdateListener;
import com.github.goldfish07.mypizza.model.MyPizza;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends AppCompatActivity implements OnCartUpdateListener {

    private RelativeLayout rootCart;
    private List<MyPizza> myPizzaList = new ArrayList<>();
    private RelativeLayout totalLayout;
    private TextView totalPriceTxt;
    MaterialButton payNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rootCart = findViewById(R.id.rootCart);
        payNowBtn = findViewById(R.id.payNowBtn);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        totalLayout = findViewById(R.id.totalLayout);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        payNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch(new Intent(ViewCartActivity.this, OrderPlacedActivity.class));
                myPizzaList.clear();

            }
        });

        myPizzaList = getIntent().getParcelableArrayListExtra(INTENT_KEY_MY_PIZZA_OBJ);
//        if(Utils.isServerDBAvailable(this)){
//            myPizzaList = Utils.getJsonServer(this);
//        } else {
//            if (myPizza != null) {
//                myPizzaList.add(myPizza);
//                Utils.saveJson(this, (ArrayList<MyPizza>) myPizzaList);
//            } else {
//                emptyCart();
//            }
//        }
        if (myPizzaList != null && myPizzaList.isEmpty()) {
            emptyCart();
        } else {
            MyPizzaAdapter pizzaAdapter = new MyPizzaAdapter(this, myPizzaList, this);
//            recyclerView.getRecycledViewPool().setMaxRecycledViews(view, 0);
            recyclerView.setAdapter(pizzaAdapter);
        }
    }

    public ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode()  == REQUEST_CODE_ORDER_PLACED) {
                    setResult(REQUEST_CODE_CART_EMPTY);
                    finish();
                }
            });

    public void emptyCart() {
        totalLayout.setVisibility(View.GONE);
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.layout_your_cart_is_empty, null);
        RelativeLayout.LayoutParams param = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(param);
        rootCart.addView(view);
    }

    @Override
    public void onUpdate(int size) {
        if (size == 0) {
            emptyCart();
            myPizzaList.clear();
            setResult(REQUEST_CODE_CART_EMPTY);
        }
    }

    @Override
    public void onTotalPrice(int totalPrice) {
        String price = String.valueOf(totalPrice);
        Log.e("totalPrice", price);
        totalPriceTxt.setText(price);
    }
}
