package com.github.goldfish07.mypizza.activity;

import static com.github.goldfish07.mypizza.Constants.INTENT_KEY_MY_PIZZA_OBJ;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.goldfish07.mypizza.MainActivity;
import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.Utils;
import com.github.goldfish07.mypizza.adapter.MyPizzaAdapter;
import com.github.goldfish07.mypizza.model.MyPizza;

import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends MainActivity implements View.OnClickListener {

    TextView myPizzaName;
    TextView myPizzaCrust;
    TextView myPizzaSize;
    TextView myPizzaPrice;

    TextView itemCounterTxt;

    CardView increaseQuantity;
    CardView decreaseQuantity;
    int counter = 1;

    RecyclerView recyclerView;

    MyPizzaAdapter pizzaAdapter;

    RelativeLayout rootCart;
    List<MyPizza> myPizzaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rootCart = findViewById(R.id.rootCart);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        pizzaAdapter = new MyPizzaAdapter(this, myPizzaList);
        recyclerView.setAdapter(pizzaAdapter);


//        myPizzaName = findViewById(R.id.myPizzaNameTxt);
//        myPizzaCrust = findViewById(R.id.myPizzaCrustTxt);
//        myPizzaSize = findViewById(R.id.myPizzaSizeTxt);
//        myPizzaPrice = findViewById(R.id.myPizzaPriceTxt);
//
//        increaseQuantity = findViewById(R.id.increaseQuantity);
//        decreaseQuantity = findViewById(R.id.decreaseQuantity);
//
//        itemCounterTxt = findViewById(R.id.itemCounter);
//
//        increaseQuantity.setOnClickListener(this);
//        decreaseQuantity.setOnClickListener(this);
//
//        myPizza = getIntent().getParcelableExtra(INTENT_KEY_MY_PIZZA_OBJ);
//        myPizzaName.setText(myPizza.getName());
//        myPizzaCrust.setText(myPizza.getCrust());
//        myPizzaSize.setText(myPizza.getSize());
//        myPizzaPrice.setText(String.valueOf(myPizza.getPrice()));
//        itemCounterTxt.setText(String.valueOf(counter));
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.increaseQuantity) {
            if (counter <= 10) {
                ++counter;
                decreaseQuantity.setEnabled(true);
            }
            itemCounterTxt.setText(String.valueOf(counter));
            myPizzaPrice.setText(String.valueOf(myPizza.getPrice() * counter)); //update price as counter goes up
        } else if (view.getId() == R.id.decreaseQuantity) {
            if (counter == 1) {
                decreaseQuantity.setEnabled(false);
                myPizzaPrice.setText(String.valueOf(myPizza.getPrice())); //update price as counter goes down
            } else {
                --counter;
                itemCounterTxt.setText(String.valueOf(counter));
                myPizzaPrice.setText(String.valueOf(Integer.parseInt(myPizzaPrice.getText().toString()) - myPizza.getPrice())); //update price as counter goes down
            }
        }
    }

    public void emptyCart() {
        @SuppressLint("InflateParams")
        // position on right bottom
        View view = getLayoutInflater().inflate(R.layout.layout_your_cart_is_empty, null);
        RelativeLayout.LayoutParams param = new
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(param);
        rootCart.addView(view);
    }
}
