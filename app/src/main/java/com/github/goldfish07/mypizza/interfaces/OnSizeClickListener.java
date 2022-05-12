package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.model.MyPizza;
import com.github.goldfish07.mypizza.model.Sizes;

/**
 * trigger when crust size {@link com.github.goldfish07.mypizza.adapter.SizeAdapter} clicked
 */
public interface OnSizeClickListener {

    /**
     * @param sizes is {@link Sizes} object
     */
    void onSizeSelected(Sizes sizes);
    /**
     * @param myPizza is {@link MyPizza} object
     */
    void onSizeSelected(MyPizza myPizza);

}
