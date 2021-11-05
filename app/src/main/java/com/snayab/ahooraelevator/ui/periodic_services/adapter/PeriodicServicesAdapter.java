package com.snayab.ahooraelevator.ui.periodic_services.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.helpers.TextHelper;
import com.snayab.ahooraelevator.ui.periodic_services.model.PeriodicServicesResponse;

import java.util.List;

public class PeriodicServicesAdapter extends RecyclerView.Adapter<PeriodicServicesAdapter.ViewHolder> {

    private Context context;

    private List<PeriodicServicesResponse.PeriodicServices> services;


    public PeriodicServicesAdapter(Context context, List<PeriodicServicesResponse.PeriodicServices> services) {
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_periodic_services, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PeriodicServicesResponse.PeriodicServices service = services.get(position);
        holder.textViewListServices.setText(TextHelper.perisanNumber(service.getServed_at()));

        if (service.getDone()) {
            holder.imageViewService.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_checked));
            holder.textViewListServices.setTextColor(context.getResources().getColor(R.color.grey_700));

        } else {
            holder.imageViewService.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_unchecked));
            holder.textViewListServices.setTextColor(context.getResources().getColor(R.color.grey_400));

        }

    }

    @Override
    public int getItemCount() {
        return services.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewListServices;
        private ImageView imageViewService;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewListServices = itemView.findViewById(R.id.textViewListServices);
            imageViewService = itemView.findViewById(R.id.imageViewService);
        }
    }

    public void addItems(List<PeriodicServicesResponse.PeriodicServices> services) {
        clearItems();
        this.services.addAll(services);
        notifyDataSetChanged();
    }

    private void clearItems() {
        if (services.size() > 0) {
            services.clear();
            notifyDataSetChanged();
        }
    }

}
