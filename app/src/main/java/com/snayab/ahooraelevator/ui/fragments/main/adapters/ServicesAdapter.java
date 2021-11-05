package com.snayab.ahooraelevator.ui.fragments.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.dialog.DialogServicesPopup;
import com.snayab.ahooraelevator.ui.fragments.main.model.HomePageResponse;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private Context context;

    private List<HomePageResponse.Services> services;


    public ServicesAdapter(Context context, List<HomePageResponse.Services> services) {
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_services, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomePageResponse.Services service = services.get(position);
        holder.textViewListServices.setText(service.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogServicesPopup dialogServicesPopup = new DialogServicesPopup(context);
                dialogServicesPopup.setTitle(service.getTitle());
                dialogServicesPopup.setCancelable(true);
                dialogServicesPopup.setDescription(service.getDescription());
                dialogServicesPopup.setOnConfirmClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogServicesPopup.dismiss();
                    }
                });
                dialogServicesPopup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return services.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewListServices;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewListServices = itemView.findViewById(R.id.textViewListServices);
        }
    }

    public void addItems(List<HomePageResponse.Services> services) {
        this.services.addAll(services);
        notifyDataSetChanged();
    }

}
