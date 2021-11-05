package com.snayab.ahooraelevator.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.ui.about_company.model.AboutCompanyResponse;

import java.util.List;


public class AdapterAboutCompany extends RecyclerView.Adapter<AdapterAboutCompany.ViewHolder> {

    private Context context;
    private List<AboutCompanyResponse.AboutUs.Portfolio> portfolio;

    private OnOtherProductItemSelected onOtherProductItemSelected;

    public void setOnOtherProductItemSelected(OnOtherProductItemSelected onOtherProductItemSelected){
        this.onOtherProductItemSelected = onOtherProductItemSelected;
    }
    public AdapterAboutCompany(Context context, List<AboutCompanyResponse.AboutUs.Portfolio> portfolio) {
        this.context = context;
        this.portfolio = portfolio;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_other_apps, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

//        NewsResponse.News.GeneralNews newsResponse = newsList.get(position);

        AboutCompanyResponse.AboutUs.Portfolio portfolioResponse = portfolio.get(position);

        Glide.with(context).load(BuildConfig.SNAAGRIN_SITE_URL + portfolioResponse.getPicture()).into(holder.imageViewApp);
        holder.textViewApp.setText(portfolioResponse.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOtherProductItemSelected.onOtherProductItemSelected(portfolioResponse.getPackage_name(),portfolioResponse.getLink());
            }
        });

    }

    @Override
    public int getItemCount() {
        return portfolio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewApp;
        TextView textViewApp;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewApp = itemView.findViewById(R.id.imageViewApp);
            textViewApp = itemView.findViewById(R.id.textViewApp);


        }

    }

    public void addItems(List<AboutCompanyResponse.AboutUs.Portfolio> portfolio) {
        this.portfolio.addAll(portfolio);
        notifyDataSetChanged();
    }

    public interface OnOtherProductItemSelected {
        void onOtherProductItemSelected(String packageName, String link);

    }

}