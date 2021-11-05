package com.snayab.ahooraelevator.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.snayab.ahooraelevator.R;

import java.util.List;



public class AdapterStringSpinner extends ArrayAdapter<String> {

    private LayoutInflater inflater;

    public AdapterStringSpinner(@NonNull Context context, int resourceId, int textViewId, @NonNull List<String> list) {
        super(context, resourceId, textViewId, list);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_popup_spinner, null, true);
        }
        String rowItem = getItem(position);

        TextView txtTitle = convertView.findViewById(R.id.tvTitle);
        txtTitle.setText(rowItem+"");

        return convertView;
    }
}




