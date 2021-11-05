package com.snayab.ahooraelevator.ui.fragments.category.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.ui.fragments.category.model.ElevatorTypesResponse;

import java.util.ArrayList;
import java.util.List;


public class AdapterMainCategoriesList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = this.getClass().getName();

    private List<ElevatorTypesResponse.Elevators> list;
    private Context mContext;
    private int visibleThreshold = 5;
    private OnMainCategoryClickListiner onMainCategoryClicked;

    public interface OnMainCategoryClickListiner {
        void onMainCategoryClicked(int position);
    }

    public void setOnMainCategoryClickListiner(OnMainCategoryClickListiner onMainCategoryClicked) {
        this.onMainCategoryClicked = onMainCategoryClicked;
    }

    public AdapterMainCategoriesList(Context context, List<ElevatorTypesResponse.Elevators> list) {
        this.list = list;
        this.mContext = context;
    }

    private class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageView imageViewLogo;
        TextView tvTitle;

        private ViewHolderItem(View v) {
            super(v);
            imageViewLogo = v.findViewById(R.id.imageViewLogo);
            tvTitle = v.findViewById(R.id.tvTitle);
        }
    }


    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_elevator_categories_list, parent, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final ViewHolderItem view = (ViewHolderItem) holder;
        ElevatorTypesResponse.Elevators elevators = list.get(position);

        view.tvTitle.setText(elevators.getName() + "");
        Glide.with(mContext).load(BuildConfig.SITE_URL + elevators.getPicture()).into(view.imageViewLogo);

        view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMainCategoryClicked.onMainCategoryClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addItems(ArrayList<ElevatorTypesResponse.Elevators> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }


    public void clearAll() {
        this.list.clear();
        notifyDataSetChanged();
    }

}