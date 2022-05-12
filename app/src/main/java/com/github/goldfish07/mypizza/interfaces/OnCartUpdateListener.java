package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.adapter.MyPizzaAdapter;

/**
 * update cart from {@link MyPizzaAdapter}
 */
public interface OnCartUpdateListener {
    /**
     * @param size is the {@link MyPizzaAdapter#getItemCount()}
     */
    void onUpdate(int size);

    /**
     * @param totalPrice is the price from each list of pizza
     */
    void onTotalPrice(int totalPrice);
}
