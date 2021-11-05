package com.snayab.ahooraelevator.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.helpers.MathHelper;
import com.snayab.ahooraelevator.ui.contracts.model.ContractLastResponse;
import com.snayab.ahooraelevator.helpers.TextHelper;

import java.util.List;


public class AdapterPaymentLast extends RecyclerView.Adapter<AdapterPaymentLast.ViewHolder> {

    private Context context;
    List<ContractLastResponse.LastContract.Payments> listPaymentLast;
    MathHelper mathHelper;


    public AdapterPaymentLast(Context context, List<ContractLastResponse.LastContract.Payments> listPaymentLast) {
        this.context = context;
        this.listPaymentLast = listPaymentLast;
        mathHelper = new MathHelper(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_payment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ContractLastResponse.LastContract.Payments payments = listPaymentLast.get(position);
        holder.textViewPaymentDate.setText(TextHelper.perisanNumber(payments.getSettled_at()) + " در تاریخ ");
        holder.textViewPaymentPrice.setText(" مبلغ " + TextHelper.perisanNumber(mathHelper.convertMoneyToWithComma("" + payments.getAmount())) + " تومان " + (payments.getType().equals("cash") ? " نقد " : " چک "));

    }

    @Override
    public int getItemCount() {
        return listPaymentLast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewApp;
        TextView textViewPaymentDate, textViewPaymentPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPaymentDate = itemView.findViewById(R.id.textViewPaymentDate);
            textViewPaymentPrice = itemView.findViewById(R.id.textViewPaymentPrice);
        }
    }

    public void addItems(List<ContractLastResponse.LastContract.Payments> list) {
        this.listPaymentLast.addAll(list);
        notifyDataSetChanged();
    }

    public void clearAll() {
        if (listPaymentLast.size() > 0)
            this.listPaymentLast.clear();
    }


}