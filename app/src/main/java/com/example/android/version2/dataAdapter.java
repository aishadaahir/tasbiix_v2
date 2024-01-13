package com.example.android.version2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {
    private Context context;
    private ArrayList title,count,date,type;
    String todayString;


    public dataAdapter(Context context,ArrayList title,ArrayList count,ArrayList date,ArrayList type,String todayString){
        this.context = context;
        this.title = title;
        this.count = count;
        this.date = date;
        this.todayString = todayString;
        this.type = type;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.datalaout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Log.e("dateformated",String.valueOf(date.get(position)));
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE,dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dates = inputFormat.parse(String.valueOf(date.get(position)));
            assert dates != null;
            String formattedDate = outputFormat.format(dates);
//            System.out.println("Formatted Date: " + formattedDate);
            Log.e("dateformated",formattedDate);
            holder.passtime.setText(formattedDate);
//            if(Objects.equals(todayString, formattedDate)){
//                holder.title.setText(String.valueOf(title.get(position)));
//                holder.count.setText(String.valueOf(count.get(position)));
////                Log.e("dateformated",String.valueOf(date.get(position)));
//                holder.date.setText(String.valueOf(date.get(position)));
//                holder.type.setText(String.valueOf(type.get(position)));
//            }
//            else{
//                holder.datslayout.setVisibility(View.GONE);
//            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
//Log.e("rrtedfgdffd",date.get(position).toString());
//        if(Objects.equals(todayString, date.get(position))){
//            holder.title.setText(String.valueOf(title.get(position)));
//            holder.count.setText(String.valueOf(count.get(position)));
////                Log.e("dateformated",String.valueOf(date.get(position)));
//            holder.date.setText(String.valueOf(date.get(position)));
//            holder.type.setText(String.valueOf(type.get(position)));
//        }

            holder.title.setText(String.valueOf(title.get(position)));
            holder.count.setText(String.valueOf(count.get(position)));
//                Log.e("dateformated",String.valueOf(date.get(position)));
            holder.date.setText(String.valueOf(date.get(position)));
            holder.type.setText(String.valueOf(type.get(position)));

    }



    @Override
    public int getItemCount() {
        return count.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,count,date,type,passtime;
        LinearLayout datslayout;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Name);
            count = itemView.findViewById(R.id.countes);
            date = itemView.findViewById(R.id.dtext);
            type = itemView.findViewById(R.id.restype);
            passtime = itemView.findViewById(R.id.passtime);
            datslayout = itemView.findViewById(R.id.datslayout);


        }



    }

}

