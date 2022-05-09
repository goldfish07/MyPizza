package com.github.goldfish07.mypizza.activity;

import static com.github.goldfish07.mypizza.Constants.INTENT_KEY_CRUSTS_OBJ;
import static com.github.goldfish07.mypizza.Constants.INTENT_KEY_PIZZA_OBJ;
import static com.github.goldfish07.mypizza.Constants.INTENT_KEY_SIZES_OBJ;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.goldfish07.mypizza.MainActivity;
import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.Pizza;
import com.github.goldfish07.mypizza.model.Sizes;

import java.util.ArrayList;


public class CustomizeActivity extends MainActivity {

    TextView pizzaName;
    TextView pizzaDesc;
    ImageView foodTypeIndicator;
    RadioGroup crustsRadioGroup;
    ArrayList<Parcelable> crusts;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_customize);
        pizzaName = findViewById(R.id.pizzaName);
        pizzaDesc = findViewById(R.id.pizzaDesc);
        foodTypeIndicator = findViewById(R.id.foodTypeIndicator);
        crustsRadioGroup = findViewById(R.id.crust_recyclerView);

        Pizza pizza = getIntent().getParcelableExtra(INTENT_KEY_PIZZA_OBJ);
        crusts = getIntent().getParcelableArrayListExtra(INTENT_KEY_CRUSTS_OBJ);


//        Log.e("pizzaName", String.valueOf(crusts.size()));
        pizzaName.setText(pizza.getName());
        pizzaDesc.setText(pizza.getDescription());
        foodTypeIndicator.setImageResource(pizza.getVeg() ? R.drawable.ic_veg : R.drawable.ic_non_veg);

        RadioButton radioButton = null;
        for (int i = 0; i < crusts.size(); i++) {
            radioButton = new RadioButton(this);
            radioButton.setId(i);
            Crusts crust = (Crusts) crusts.get(i);
            radioButton.setText(crust.getName() + " "); //+i
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            radioButton.setLayoutParams(params);
            crustsRadioGroup.addView(radioButton);
        }
//        if (radioButton != null)
//            radioButton.setOnClickListener(this);

    }
//
//    @SuppressLint("ResourceType")
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == 0) {
//            applySizesNew(2);
//        } else if (view.getId() == 1) {
//            applySizesNew(1);
//        }
//    }

//    @SuppressLint("SetTextI18n")
//    public void applySizes() {
//        if (sizeRadioGroup != null) {
//            sizeRadioGroup.removeAllViews();
//        }
//        ArrayList<Parcelable> sizes = getIntent().getParcelableArrayListExtra(INTENT_KEY_SIZES_OBJ);
//        Sizes sizess = (Sizes) sizes.get(0);
//        Log.e("pizzaName", String.valueOf(sizess.getId()));
//
//        for (int i = 0; i < sizes.size(); i++) {
//            RadioButton radioButton = new RadioButton(this);
//            radioButton.setId(View.generateViewId());
//            Sizes size = (Sizes) sizes.get(i);
//            radioButton.setText(size.getName() + " "); //+i
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
//            radioButton.setLayoutParams(params);
//            sizeRadioGroup.addView(radioButton);
//        }
//    }

//    public void applySizesNew(int length) {
//
//        ArrayList<Parcelable> sizes = getIntent().getParcelableArrayListExtra(INTENT_KEY_SIZES_OBJ);
//        Sizes sizess = (Sizes) sizes.get(0);
//        Log.e("pizzaName", String.valueOf(sizess.getId()));
//
//        for (int i = 0; i < length; i++) {
//            RadioButton radioButton = new RadioButton(this);
//            radioButton.setId(View.generateViewId());
//            Sizes size = (Sizes) sizes.get(i);
//            radioButton.setText(size.getName() + " "); //+i
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
//            radioButton.setLayoutParams(params);
////            sizeRadioGroup.addView(radioButton);
//        }
//    }
}
