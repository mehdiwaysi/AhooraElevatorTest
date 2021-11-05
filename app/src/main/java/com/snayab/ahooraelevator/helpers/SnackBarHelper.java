package com.snayab.ahooraelevator.helpers;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.R;

public class SnackBarHelper {

    public static void showSnackBar(Context context, View view, CharSequence text, int duration) {

        Snackbar snackbar = Snackbar.make(view, text, duration);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(R.id.snackbar_text);
        textView.setTextSize(16);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.snackbar_background));
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();

    }

}
