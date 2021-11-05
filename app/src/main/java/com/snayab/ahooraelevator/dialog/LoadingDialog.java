package com.snayab.ahooraelevator.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.snayab.ahooraelevator.R;


public class LoadingDialog {

    private Context context;
    private AlertDialog alertDialog;
    private View customView;
    private TextView textView;

    public LoadingDialog(Context context) {
        this.context = context;
        this.alertDialog = new AlertDialog.Builder(context).create();
        this.customView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        this.alertDialog.setView(customView);
        this.textView = customView.findViewById(R.id.tvLoading);
    }


    public void show() {
        this.alertDialog.show();
    }


    public void dismiss() {
        this.alertDialog.dismiss();
    }

    public void setText(String text) {
        this.textView.setText(text + "");
    }

    public void setTextTypeface(Typeface tf) {
        this.textView.setTypeface(tf);
    }
}
