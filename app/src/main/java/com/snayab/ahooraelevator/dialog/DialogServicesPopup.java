package com.snayab.ahooraelevator.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Html;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.snayab.ahooraelevator.databinding.PopupShowDescriptionBinding;


public class DialogServicesPopup {

    private Context context;
    private AlertDialog alertDialog;

    PopupShowDescriptionBinding binding;

    public DialogServicesPopup(Context context) {
        this.context = context;
        this.alertDialog = new AlertDialog.Builder(context).create();

        binding = PopupShowDescriptionBinding.inflate(((Activity) context).getLayoutInflater());
        View view = binding.getRoot();


        alertDialog.setView(view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    public DialogServicesPopup setCancelable(boolean cancelable){
        alertDialog.setCancelable(cancelable);
        alertDialog.setCanceledOnTouchOutside(cancelable);
        return this;
    }


    public DialogServicesPopup setDescription(String description) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvDescription.setText(Html.fromHtml("" + description, Html.FROM_HTML_MODE_COMPACT));
        } else {
            binding.tvDescription.setText(Html.fromHtml("" + description));
        }
        return this;

    }


    public DialogServicesPopup setTitle(String title) {
        binding.tvTitle.setText(title);
        return this;
    }


    public DialogServicesPopup setOnConfirmClick(View.OnClickListener listener) {
        binding.btnConfirm.setOnClickListener(listener);
        return this;
    }


    public void show() {
        this.alertDialog.show();
    }

    public void dismiss() {
        this.alertDialog.dismiss();
    }


}
