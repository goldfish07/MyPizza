package com.github.goldfish07.mypizza.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.interfaces.OnAddPizzaListener;
import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.Pizza;
import com.google.android.material.button.MaterialButton;

import java.util.List;

/**
 * Adapter for {@link Pizza} object which display list of available pizza
 */
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> implements View.OnClickListener {

    private final Context context;
    private final List<Pizza> pizza;
    private int position;
    private final OnAddPizzaListener onAddPizzaListener;

    /**
     * Constructs a new {@link PizzaAdapter}.
     * @param context is the activity context
     * @param pizza is {@link List<Pizza>} object
     * @param onAddPizzaListener is event listener when pizza added to cart
     */
    public PizzaAdapter(Context context, List<Pizza> pizza, OnAddPizzaListener onAddPizzaListener) {
        this.context = context;
        this.pizza = pizza;
        this.onAddPizzaListener = onAddPizzaListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_pizza, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.pizzaName.setText(pizza.get(position).getName());
        holder.pizzaDesc.setText(pizza.get(position).getDescription());
        holder.foodTypeIndicator.setImageResource(pizza.get(position).getVeg() ?
                R.drawable.ic_veg : R.drawable.ic_non_veg);
        holder.btnAddItem.setOnClickListener(this);
        //holder.txtCustomize.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return pizza.size();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddItem) {
            onAddPizzaListener.onAddPizza(pizza.get(position));
        }
//        else if (view.getId() == R.id.txtCustomize) {
//            Intent intent = new Intent(context, CustomizeActivity.class);
//            Pizza pizzas = new Pizza(pizza.get(position));
//            intent.putExtra(INTENT_KEY_PIZZA_OBJ, pizzas);
//            intent.putParcelableArrayListExtra(INTENT_KEY_CRUSTS_OBJ,
//                    (ArrayList<? extends Parcelable>) pizzas.getCrusts());
//            intent.putParcelableArrayListExtra(INTENT_KEY_SIZES_OBJ,
//                    (ArrayList<? extends Parcelable>) pizzas.getSizes());
//
//            context.startActivity(intent);
//        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView pizzaName;
        private final TextView pizzaDesc;
        private final ImageView foodTypeIndicator;
        private final MaterialButton btnAddItem;
//        private final TextView txtCustomize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaDesc = itemView.findViewById(R.id.pizzaDesc);
            foodTypeIndicator = itemView.findViewById(R.id.foodTypeIndicator);
            btnAddItem = itemView.findViewById(R.id.btnAddItem);
//            txtCustomize = itemView.findViewById(R.id.txtCustomize);
        }
    }
}
