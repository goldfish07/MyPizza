package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.model.MyPizza;

public interface PizzaPriceListener {
    void onPriceUpdate(MyPizza myPizza, int totalPrice);
}
