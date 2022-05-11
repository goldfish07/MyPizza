package com.github.goldfish07.mypizza.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.interfaces.OnCartUpdateListener;
import com.github.goldfish07.mypizza.model.MyPizza;

import java.util.List;

public class MyPizzaAdapter extends RecyclerView.Adapter<MyPizzaAdapter.ViewHolder> {

    Context context;
    List<MyPizza> myPizzas;
    int counter = 1;
    ViewHolder holder;
    int totalPrice;

    OnCartUpdateListener onCartUpdateListener;

    public MyPizzaAdapter(Context context, List<MyPizza> myPizzas, OnCartUpdateListener onCartUpdateListener) {
        this.context = context;
        this.myPizzas = myPizzas;
        this.onCartUpdateListener = onCartUpdateListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_my_pizza, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.holder = holder;
        holder.pizzaName.setText(myPizzas.get(position).getName());
        holder.pizzaCrust.setText(myPizzas.get(position).getCrust());
        holder.pizzaSize.setText(myPizzas.get(position).getSize());
        holder.pizzaPrice.setText(String.valueOf(myPizzas.get(position).getPrice()));
        holder.itemCounterTxt.setText(String.valueOf(counter));
        if(totalPrice == 0){
            totalPrice =  myPizzas.get(position).getPrice();
        } else {
            totalPrice = totalPrice + myPizzas.get(position).getPrice();

        }
        if(position + 1 == getItemCount()){
            onCartUpdateListener.onTotalPrice(totalPrice);
        }
        Log.e("position, getItemCount", position + " "+ getItemCount());

        holder.increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter <= 10) {
                    ++counter;
                    holder.decreaseQuantity.setEnabled(true);
                }
                holder.itemCounterTxt.setText(String.valueOf(counter));
                holder.pizzaPrice.setText(String.valueOf(myPizzas.get(position).getPrice() * counter)); //update price as counter goes up

            }
        });
        holder.decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 1) {
                    // holder.decreaseQuantity.setEnabled(false);
                    holder.pizzaPrice.setText(String.valueOf(myPizzas.get(position).getPrice())); //update price as counter goes down
                    myPizzas.remove(position);
                    notifyItemRemoved(position);
                    onCartUpdateListener.onUpdate(getItemCount());
                } else {
                    --counter;
                    holder.itemCounterTxt.setText(String.valueOf(counter));
                    holder.pizzaPrice.setText(String.valueOf(Integer.parseInt(holder.pizzaPrice.getText().toString()) - myPizzas.get(position).getPrice())); //update price as counter goes down
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return myPizzas.size();
    }


//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.increaseQuantity) {
//
//        } else if (view.getId() == R.id.decreaseQuantity) {
//
//
//        }
//    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView pizzaName;
        TextView pizzaCrust;
        ImageView pizzaTypeIndicatorImg;
        TextView pizzaSize;
        TextView pizzaPrice;

        CardView increaseQuantity;
        CardView decreaseQuantity;
        TextView itemCounterTxt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.myPizzaNameTxt);
            pizzaCrust = itemView.findViewById(R.id.myPizzaCrustTxt);
            pizzaSize = itemView.findViewById(R.id.myPizzaSizeTxt);
            pizzaTypeIndicatorImg = itemView.findViewById(R.id.myPizzaIndicatorImg);
            pizzaPrice = itemView.findViewById(R.id.myPizzaPriceTxt);

            increaseQuantity = itemView.findViewById(R.id.increaseQuantity);
            decreaseQuantity = itemView.findViewById(R.id.decreaseQuantity);
            itemCounterTxt = itemView.findViewById(R.id.itemCounter);
//            increaseQuantity.setOnClickListener(MyPizzaAdapter.this);
//            decreaseQuantity.setOnClickListener(MyPizzaAdapter.this);
        }
    }
}
