package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.model.MyPizza;

/**
 * trigger
 */
public interface PizzaPriceListener {
    /**
     * @param myPizza is {@link MyPizza} object
     * @param totalPrice is price of all pizza added to cart
     */
    void onPriceUpdate(MyPizza myPizza, int totalPrice);
}
