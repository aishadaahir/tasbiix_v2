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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {
    private Context context;
    private ArrayList title,count,date,type;
    String todayString,yesterday;
    int positionlastyear,positionlastmonth,positionlasttoday,positionlastweek,positionlonger,positionlastyesterday;
    int countlastyear,countlastmonth,countlasttoday,countlastweek,countlastyesterday,countlonger;
    int today=0;
    int yester=0;
    int pos=-1;
    int posy=-1;
    MyViewHolder hold,hold2;


    public dataAdapter(Context context,ArrayList title,ArrayList count,ArrayList date,ArrayList type,String todayString,String yesterday,
    int countlastmonth,int positionlastmonth,int countlastyear,int positionlastyear,int countlastweek,int positionlastweek,int countlasttoday,
                       int positionlasttoday,int countlastyesterday,int positionlastyesterday,int countlonger,int positionlonger){
        this.context = context;
        this.title = title;
        this.count = count;
        this.date = date;
        this.todayString = todayString;
        this.yesterday = yesterday;
        this.type = type;
        this.positionlastyear = positionlastyear;
        this.countlastyear = countlastyear;
        this.positionlastmonth = positionlastmonth;
        this.countlastmonth = countlastmonth;
        this.positionlasttoday = positionlasttoday;
        this.countlasttoday = countlasttoday;
        this.positionlastweek = positionlastweek;
        this.countlastweek = countlastweek;
        this.positionlastyesterday = positionlastyesterday;
        this.countlastyesterday = countlastyesterday;
        this.positionlonger = positionlonger;
        this.countlonger = countlonger;


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
        holder.headlayout.setVisibility(View.GONE);
        Log.e("dateformated",String.valueOf(date.get(position)));
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE,dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dates = inputFormat.parse(String.valueOf(date.get(position)));
            assert dates != null;
            String formattedDate = outputFormat.format(dates);
            Log.e("dateformated",formattedDate);
            if(!String.valueOf(positionlastmonth).equals("-1")){
                if(Objects.equals(positionlastmonth, position)){
                    holder.total.setText("tasbiix: "+String.valueOf(countlastmonth));
                    holder.passtime.setText("Last Month");
                    holder.headlayout.setVisibility(View.VISIBLE);
                }
            }
            if(!String.valueOf(positionlastyear).equals("-1")){
                if(Objects.equals(positionlastyear, position)){
                    holder.total.setText("tasbiix: "+String.valueOf(countlastyear));
                    holder.passtime.setText("Last Year");
                    holder.headlayout.setVisibility(View.VISIBLE);
                }

            }
            if(!String.valueOf(positionlastweek).equals("-1")){
                if(Objects.equals(positionlastweek, position)){
                    holder.total.setText("tasbiix: "+String.valueOf(countlastweek));
                    holder.passtime.setText("Last Week");
                    holder.headlayout.setVisibility(View.VISIBLE);
                }

            }
            if(!String.valueOf(positionlasttoday).equals("-1")){
                if(Objects.equals(positionlasttoday, position)){
                    holder.total.setText("tasbiix: "+String.valueOf(countlasttoday));
                    holder.passtime.setText("Today");
                    holder.headlayout.setVisibility(View.VISIBLE);
                }

            }
            if(!String.valueOf(positionlastyesterday).equals("-1")){
                if(Objects.equals(positionlastyesterday, position)){
                    holder.total.setText("tasbiix: "+String.valueOf(countlastyesterday));
                    holder.passtime.setText("Yesterday");
                    holder.headlayout.setVisibility(View.VISIBLE);
                }

            }
            if(!String.valueOf(positionlonger).equals("-1")){
                if(Objects.equals(positionlonger, position)){
                    holder.total.setText("tasbiix: "+String.valueOf(countlonger));
                    holder.passtime.setText("Longer");
                    holder.headlayout.setVisibility(View.VISIBLE);
                }

            }
//            else{
//                holder.headlayout.setVisibility(View.GONE);
//            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


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

        TextView title,count,date,type,passtime,total;
        LinearLayout datslayout;
        ConstraintLayout headlayout;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Name);
            count = itemView.findViewById(R.id.countes);
            date = itemView.findViewById(R.id.dtext);
            type = itemView.findViewById(R.id.restype);
            passtime = itemView.findViewById(R.id.passtime);
            total = itemView.findViewById(R.id.total);
            datslayout = itemView.findViewById(R.id.datslayout);
            headlayout = itemView.findViewById(R.id.headlayout);

        }
//        public  void changes(int pos,int today){
//            if (pos >= 0 && pos < getItemCount()) {
//                MyViewHolder holder = (MyViewHolder) recyclerView.findViewHolderForAdapterPosition(pos);
//                Log.e("resdrvsretgsdf", String.valueOf(holder));
//                if (holder != null) {
////                    holder.total.setText(Integer.toString(today));
//                }
//            }
//        }
        @SuppressLint("SetTextI18n")
        private void changes(MyViewHolder holder, int position,int todays) {
//            int updatedTotal = updatedTotals.get(position);
//            Log.e("resdrvsretgsdf", String.valueOf(updatedTotal));
            holder.total.setText(Integer.toString(todays));
        }


    }

}

