package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.MyPizza;
import com.github.goldfish07.mypizza.model.Sizes;

public interface OnSizeClickListener {
    void onSizeSelected(Sizes sizes);
    void onSizeSelected(MyPizza myPizza);

}
