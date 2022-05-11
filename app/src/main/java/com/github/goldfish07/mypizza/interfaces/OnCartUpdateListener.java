package com.github.goldfish07.mypizza.interfaces;

public interface OnCartUpdateListener {
    void onUpdate(int size);
    void onTotalPrice(int totalPrice);
}
