package com.snayab.ahooraelevator.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.snayab.ahooraelevator.R;


public class DialogMessageConfirm {

    public enum DialogType {
        DIALOG_MESSAGE_INFO,
        DIALOG_MESSAGE_SUCCESS,
        DIALOG_MESSAGE_WARNING,
        DIALOG_MESSAGE_DANGER
    }

    private Context context;
    private View customView;
    private AlertDialog alertDialog;
    private DialogType dialogType;

    private TextView tvTitle, tvDescription;
    private ImageView imageView;
    private Button btnCancel, btnConfirm;


    public DialogMessageConfirm(Context context, DialogType dialogType) {

        this.context = context;
        this.alertDialog = new AlertDialog.Builder(context).create();
        this.customView = LayoutInflater.from(context).inflate(R.layout.dialog_message_confirm, null);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.alertDialog.setView(customView);
        this.dialogType = dialogType;
        tvTitle = customView.findViewById(R.id.tvTitle);
        tvDescription = customView.findViewById(R.id.tvDescription);
        imageView = customView.findViewById(R.id.imageView);
        btnCancel = customView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnConfirm = customView.findViewById(R.id.btnConfirm);

        setImageViewDrawable();
    }

    public DialogMessageConfirm setOnCancelClick(View.OnClickListener onCancelClick) {
        this.btnCancel.setOnClickListener(onCancelClick);
        return this;
    }

    public DialogMessageConfirm setOnConfirmClick(View.OnClickListener onConfirmClick) {
        this.btnConfirm.setOnClickListener(onConfirmClick);
        return this;
    }

    public DialogMessageConfirm setConfirmText(String text) {
        this.btnConfirm.setText(text);
        return this;
    }


    public DialogMessageConfirm setCancelText(String text) {
        this.btnCancel.setText(text);
        return this;
    }


    public DialogMessageConfirm setTitle(String title) {
        this.tvTitle.setText(title);
        return this;
    }

    public DialogMessageConfirm setDescription(String description) {
        this.tvDescription.setText(description);
        return this;
    }

    public DialogMessageConfirm setImageView(Drawable drawable) {
        this.imageView.setImageDrawable(drawable);
        return this;
    }

    public DialogMessageConfirm setConfirmVisibility(int visibility) {
        this.btnConfirm.setVisibility(visibility);
        return this;
    }

    public DialogMessageConfirm setDecriptionVisibility(int visibility) {
        this.tvDescription.setVisibility(visibility);
        return this;
    }

    public DialogMessageConfirm setCancelVisibility(int visibility) {
        this.btnCancel.setVisibility(visibility);
        return this;
    }


    private void setImageViewDrawable() {

        switch (dialogType) {
            case DIALOG_MESSAGE_INFO: {
                this.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                this.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_message_info));
                break;
            }
            case DIALOG_MESSAGE_SUCCESS: {
                this.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                this.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_message_success));
                break;
            }
            case DIALOG_MESSAGE_WARNING: {
                this.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorWarning));
                this.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_message_warning));
                this.btnConfirm.setBackground(context.getResources().getDrawable(R.drawable.bg_badge_dialog_confirm_warning));
                break;
            }
            case DIALOG_MESSAGE_DANGER: {
                this.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorDanger));
                this.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_message_danger));

                break;
            }
        }

    }


    public void show() {
        this.alertDialog.show();
    }


    public void dismiss() {
        this.alertDialog.dismiss();
    }

    public void setCancelable(boolean isCancelable) {
        alertDialog.setCancelable(isCancelable);
    }
}
