package com.github.goldfish07.mypizza.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.github.goldfish07.mypizza.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * @author goldfish07 (Ayush Bisht)
 * 8-May-2022
 */
public class ProgressBar {

    MaterialAlertDialogBuilder builder;
    AlertDialog alertDialog;

    public void show() {
        if (alertDialog != null) {
            alertDialog.show();
        }
    }

    public void dismiss() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public static class Builder extends ProgressBar {

        public Builder(Context context) {
            builder = new MaterialAlertDialogBuilder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.getWindow().getDecorView().setBackgroundColor(
                    context.getColor(android.R.color.transparent)
            );
        }
    }
}
