package com.snayab.ahooraelevator.ui.rules.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.ui.rules.model.RulesResponseModel;

import java.util.List;

public class AdapterRules extends RecyclerView.Adapter<AdapterRules.ViewHolder> {

    private String TAG = this.getClass().getName();
    private Context context;
    private List<RulesResponseModel.Rules> listRules;


    public AdapterRules(Context context, List<RulesResponseModel.Rules> listRules) {
        this.context = context;
        this.listRules = listRules;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        RulesResponseModel.Rules listRule = listRules.get(position);

        holder.tvTitle.setText(listRule.getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvDescription.setText(Html.fromHtml("" + listRule.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvDescription.setText(Html.fromHtml("" + listRule.getDescription()));
        }

    }

    @Override
    public int getItemCount() {
        return listRules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription, tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }

    }

    public void addItems(List<RulesResponseModel.Rules> listRules) {
        this.listRules.addAll(listRules);
        notifyDataSetChanged();
    }

}