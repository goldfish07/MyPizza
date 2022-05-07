package com.github.goldfish07.mypizza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.model.Pizza;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    Context context;
    List<Pizza> pizza;

    public PizzaAdapter(Context context, List<Pizza> pizza) {
        this.context = context;
        this.pizza = pizza;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_pizza, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pizzaName.setText(pizza.get(position).getName());
        holder.pizzaDesc.setText(pizza.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return pizza.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView pizzaName;
        TextView pizzaDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaDesc = itemView.findViewById(R.id.pizzaDesc);
        }
    }
}
