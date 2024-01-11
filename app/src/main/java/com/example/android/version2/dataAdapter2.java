package com.example.android.version2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class dataAdapter2 extends RecyclerView.Adapter<dataAdapter2.MyViewHolder> {
    private Context context;
    private ArrayList<String> headerDates;
    private ArrayList<String> title;
    private ArrayList<String> count;
    private ArrayList<String> date;
    private ArrayList<String> type;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_DATA = 1;

    public dataAdapter2(Context context, ArrayList<String> headerDates, ArrayList<String> title, ArrayList<String> count, ArrayList<String> date, ArrayList<String> type) {
        this.context = context;
        this.headerDates = headerDates;
        this.title = title;
        this.count = count;
        this.date = date;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == VIEW_TYPE_HEADER) {
            View headerView = inflater.inflate(R.layout.datalaout, parent, false);
            return new MyViewHolder(headerView);
        } else {
            View dataItemView = inflater.inflate(R.layout.datalaout, parent, false);
            return new MyViewHolder(dataItemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            // Bind header data
            String headerDate = headerDates.get(position);
            holder.title.setText(headerDate);
            // Hide other views specific to data items
            holder.count.setVisibility(View.GONE);
            holder.date.setVisibility(View.GONE);
            holder.type.setVisibility(View.GONE);
        } else {
            // Calculate the data position in the original data list
            int dataPosition = position - headerDates.size();
            // Bind data item
            holder.title.setText(String.valueOf(title.get(dataPosition)));
            holder.count.setText(String.valueOf(count.get(dataPosition)));
            holder.date.setText(String.valueOf(date.get(dataPosition)));
            holder.type.setText(String.valueOf(type.get(dataPosition)));
        }
    }

    @Override
    public int getItemCount() {
        return headerDates.size() + count.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headerDates.size()) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_DATA;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView count;
        TextView date;
        TextView type;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Name);
            count = itemView.findViewById(R.id.countes);
            date = itemView.findViewById(R.id.dtext);
            type = itemView.findViewById(R.id.restype);
        }
    }
}