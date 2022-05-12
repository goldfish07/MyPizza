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
import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.MyPizza;
import com.github.goldfish07.mypizza.model.Pizza;

import java.util.List;

/**
 * Adapter for {@link MyPizza} object
 */
public class MyPizzaAdapter extends RecyclerView.Adapter<MyPizzaAdapter.ViewHolder> {

    private final Context context;
    private final List<MyPizza> myPizzas;
    private int counter = 1;
    private int totalPrice;
    private final OnCartUpdateListener onCartUpdateListener;
    int price = 0;
    RecyclerView recyclerView;

    /**
     * Constructs a new {@link MyPizzaAdapter}.
     *
     * @param context              is the activity context
     * @param myPizzas             is {@link Pizza} object List
     * @param onCartUpdateListener is listener for the cart item changes
     */
    public MyPizzaAdapter(Context context, List<MyPizza> myPizzas, OnCartUpdateListener onCartUpdateListener) {
        this.context = context;
        this.myPizzas = myPizzas;
        this.onCartUpdateListener = onCartUpdateListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        recyclerView = (RecyclerView) parent;
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_my_pizza, parent, false));
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        recyclerView.getRecycledViewPool().setMaxRecycledViews(getItemViewType(position),0);

        holder.pizzaName.setText(myPizzas.get(position).getName());
        holder.pizzaCrust.setText(myPizzas.get(position).getCrust());
        holder.pizzaSize.setText(myPizzas.get(position).getSize());
        holder.pizzaPrice.setText(String.valueOf(myPizzas.get(position).getPrice()));
        holder.itemCounterTxt.setText(String.valueOf(counter));
//        if (totalPrice == 0) {
//            totalPrice = myPizzas.get(position).getPrice();
//        } else {
        totalPrice = totalPrice + myPizzas.get(position).getPrice();
//        }
        if (position == getItemCount() - 1) { // return total price when all the items loaded
            onCartUpdateListener.onTotalPrice(totalPrice);
        }
        Log.e("position, getItemCount", position + " " + String.valueOf(getItemCount() - 1));

        holder.increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter <= 10) {
                    ++counter;
                    holder.decreaseQuantity.setEnabled(true);
                }
                holder.itemCounterTxt.setText(String.valueOf(counter));
                int price = myPizzas.get(position).getPrice() * counter;

                holder.pizzaPrice.setText(String.valueOf(price)); //update price as counter goes up
                if (getItemCount() > 1) {
                    onCartUpdateListener.onTotalPrice(updateTotalPrice(true, price, position)); // update price respect to counter
                } else {
                    onCartUpdateListener.onTotalPrice(price); // update price respect to counter
                }
            }
        });
        holder.decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                int price = 0;
                if (getItemCount() != 0 && !myPizzas.isEmpty()) {//check if list isn't empty after removing pizza
                    if (counter == 1) {
                        // holder.decreaseQuantity.setEnabled(false);
                        price = myPizzas.get(position).getPrice();
                        holder.pizzaPrice.setText(String.valueOf(price)); //update price as counter goes down
                        myPizzas.remove(position);
                        notifyDataSetChanged();
                        onCartUpdateListener.onUpdate(getItemCount());
                    } else {
                        --counter;
                        holder.itemCounterTxt.setText(String.valueOf(counter));
                        price = Integer.parseInt(holder.pizzaPrice.getText().toString()) - myPizzas.get(position).getPrice();
                        holder.pizzaPrice.setText(String.valueOf(price)); //update price as counter goes down
                        if (getItemCount() > 1) {
                            onCartUpdateListener.onTotalPrice(updateTotalPrice(false, myPizzas.get(position).getPrice(), position)); // update price respect to counter
                        } else {
                            onCartUpdateListener.onTotalPrice(price); // update price respect to counter
                        }
                    }
                }
            }
        });
    }

    ViewHolder holder;
    int newPrice = 0;

    public int updateTotalPrice(boolean isIncrease, int price, int position) {
        for (int i = 0; i < 2; i++) {
            holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(i);

            if (position != i) {
                if (isIncrease) {
//                    Log.e("textView", holder.itemCounterTxt.getText().toString());
                    newPrice = price + Integer.parseInt(holder.pizzaPrice.getText().toString());
                } else {
                    newPrice = newPrice - price;
                    Log.e("pizzaPrice", String.valueOf(newPrice));
                    Log.e("price to be Minus", String.valueOf(price - myPizzas.get(position).getPrice()));
                    Log.e("new Price", String.valueOf(newPrice));
                }
            } else {
//                if(!isIncrease){
//                    newPrice = Integer.parseInt(holder.pizzaPrice.getText().toString()) - price;
//                    Log.e("pizzaPrice",holder.pizzaPrice.getText().toString());
//                    Log.e("price to be Minus",holder.pizzaPrice.getText().toString());
//                    Log.e("new Price", String.valueOf(newPrice));
//                }

            }
        }
        if (myPizzas.size() != 0)
            return newPrice;

        return price;
    }


//    public int updateTotalPrice(boolean isIncrease, int price, int counter) {
//        for (int i = 0; i < myPizzas.size(); i++) {
//            if (isIncrease) {
//                price = myPizzas.get(i).getPrice() * counter;
//            } else {
//                price = myPizzas.get(i).getPrice() - price;
//            }
//        }
//        if (myPizzas.size() != 1)
//            return price;
//
//        return 0;
//    }

    @Override
    public int getItemCount() {
        return Math.min(myPizzas.size(), 2); // limit to 2 items
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView pizzaName;
        private final TextView pizzaCrust;
        private final ImageView pizzaTypeIndicatorImg;
        private final TextView pizzaSize;
        private final TextView pizzaPrice;

        private final CardView increaseQuantity;
        private final CardView decreaseQuantity;
        private final TextView itemCounterTxt;


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
