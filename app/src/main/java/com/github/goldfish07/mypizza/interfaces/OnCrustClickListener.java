package com.github.goldfish07.mypizza.interfaces;

import com.github.goldfish07.mypizza.model.Crusts;
import com.github.goldfish07.mypizza.model.Pizza;

public interface OnCrustClickListener {
    void onCrustSelected(Pizza pizza, Crusts crusts);
}
