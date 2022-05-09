package com.github.goldfish07.mypizza.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.goldfish07.mypizza.MainActivity;
import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.interfaces.OnSizeClickListener;
import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.MyPizza;
import com.github.goldfish07.mypizza.model.Pizza;
import com.github.goldfish07.mypizza.model.Sizes;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    private final Context context;
    private Pizza pizza;
    private final List<Sizes> sizes;
    private final Crusts crusts;
    private int mSelectedItem = 0;
    private final OnSizeClickListener onSizeClickListener;
    int position;
    MyPizza myPizza;

    public SizeAdapter(Context context, Crusts crusts, OnSizeClickListener onSizeClickListener) {
        this.context = context;
        this.sizes = crusts.getSizes();
        this.crusts = crusts;
        this.onSizeClickListener = onSizeClickListener;
    }

    public SizeAdapter(Activity context, Pizza pizza, Crusts crusts, OnSizeClickListener onSizeClickListener) {
        this.context = context;
        this.pizza = pizza;
        this.sizes = crusts.getSizes();
        this.crusts = crusts;
        this.onSizeClickListener = onSizeClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_sizes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //holder.foodIndicator.setImageResource(crusts.get(position).);
        this.position = position;
        holder.type.setText(sizes.get(position).getName());
        holder.sizePrice.setText(String.valueOf(sizes.get(position).getPrice()));
        if (!holder.radioButton.isChecked() && position == crusts.getDefaultSize() - 1) { //for selecting default radio button
            holder.radioButton.setChecked(true);
            onSizeClickListener.onSizeSelected(sizes.get(mSelectedItem));
        }
//        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                onSizeClickListener.onSizeSelected(sizes.get(position));
//            }
//        });

        holder.radioButton.setChecked(mSelectedItem == position);
        if (holder.radioButton.isChecked()) {

            onSizeClickListener.onSizeSelected(new MyPizza(pizza, crusts, sizes.get(mSelectedItem)));
            //onSizeClickListener.onSizeSelected(sizes.get(mSelectedItem)); //update item when user click radio button
        }
    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView foodIndicator;
        private final TextView type;
        private final TextView sizePrice;

        private final MaterialRadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodIndicator = itemView.findViewById(R.id.foodIndicator);
            type = itemView.findViewById(R.id.crustType);
            sizePrice = itemView.findViewById(R.id.sizePrices);
            radioButton = itemView.findViewById(R.id.radioButton);
            radioButton.setOnClickListener(v -> {
                mSelectedItem = getAdapterPosition();
                notifyItemRangeChanged(0, sizes.size());
            });
        }
    }
}
