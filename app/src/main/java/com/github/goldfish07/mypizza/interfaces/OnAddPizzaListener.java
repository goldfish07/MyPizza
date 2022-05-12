package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.model.Pizza;
import com.github.goldfish07.mypizza.adapter.PizzaAdapter;
/**
 * interface trigger when pizza is added {@link PizzaAdapter}
 */
public interface OnAddPizzaListener {
    /**
     * @param pizza is {@link Pizza} object
     */
    void onAddPizza(Pizza pizza);
}
