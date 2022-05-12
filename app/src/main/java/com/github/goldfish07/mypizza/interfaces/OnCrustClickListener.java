package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.Pizza;

/**
 * trigger when crust {@link com.github.goldfish07.mypizza.adapter.CrustAdapter} clicked
 */
public interface OnCrustClickListener {
    /**
     * @param pizza is {@link Pizza} object
     * @param crusts is {@link Crusts} object
     */
    void onCrustSelected(Pizza pizza, Crusts crusts);
}
