package com.github.goldfish07.mypizza.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.goldfish07.mypizza.R;
import com.github.goldfish07.mypizza.interfaces.OnCrustClickListener;
import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.Pizza;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.List;

/**
 * Adapter for {@link Crusts} object
 */
public class CrustAdapter extends RecyclerView.Adapter<CrustAdapter.ViewHolder> {

    private int mSelectedItem = 0;
    private final Context context;
    private final List<Crusts> crusts;
    private final Pizza pizza;
    private final OnCrustClickListener onCrustClickListener;
    int position;

    /**
     * Constructs a new {@link CrustAdapter}.
     * @param context is the activity context
     * @param pizza is {@link Pizza} object
     * @param onCrustClickListener is event listener for the crust selection
     */
    public CrustAdapter(Context context, Pizza pizza, OnCrustClickListener onCrustClickListener) {
        this.context = context;
        this.pizza = pizza;
        this.crusts = pizza.getCrusts();
        this.onCrustClickListener = onCrustClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_crusts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;

        if (!holder.radioButton.isChecked() && position == pizza.getDefaultCrust() - 1) { //for selecting default radio button
            holder.radioButton.setChecked(true);
            onCrustClickListener.onCrustSelected(pizza, crusts.get(position));
        }

        holder.radioButton.setChecked(mSelectedItem == position);

        if (holder.radioButton.isChecked()) {
            onCrustClickListener.onCrustSelected(pizza, crusts.get(position));
        }

        //holder.foodIndicator.setImageResource(crusts.get(position).);
        holder.type.setText(crusts.get(position).getName());

//        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                onCrustClickListener.onCrustSelected(crusts.get(position));
//            }
//        });
        holder.radioButton.setTag(mSelectedItem);
    }

//    public void uncheckRadioBtn(int position) {
//        ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(position);
//        for (int i = 0; i < crusts.size(); i++) {
//            if (holder.radioButton.isChecked()) {
//                holder.radioButton.setChecked(false);
//            }
//        }
//    }

    @Override
    public int getItemCount() {
        return crusts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodIndicator;
        private final TextView type;
        private final MaterialRadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodIndicator = itemView.findViewById(R.id.foodIndicator);
            type = itemView.findViewById(R.id.crustType);
            radioButton = itemView.findViewById(R.id.radioButton);
            radioButton.setOnClickListener(v -> {
                mSelectedItem = getAdapterPosition();
                notifyItemRangeChanged(0, crusts.size());
            });
        }
    }
}
