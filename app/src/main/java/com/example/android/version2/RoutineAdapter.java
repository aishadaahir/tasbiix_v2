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

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.MyViewHolder> {
    private Context context;
    private ArrayList lap,counts,Name;


    public RoutineAdapter(Context context,ArrayList lap,ArrayList counts,ArrayList Name){
        this.context = context;
        this.lap = lap;
        this.counts = counts;
        this.Name = Name;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listlayout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.Name.setText(String.valueOf(Name.get(position)));
        holder.counts.setText(String.valueOf(counts.get(position)));
        holder.lap.setText(String.valueOf(lap.get(position)));
        holder.Accountnum.setText("Count");
        holder.accountnum.setText("Lap");


    }


    /// getRandomColor get random colors the usual reach is 256 but i limited it to 100 and you can set it to 156
    ///setRandomGradientBackground  get two random colors from getRandomColor then produces gradient
    private void setRandomGradientBackground(View myview) {
        int color1 = getRandomColor();
        int color2 = getRandomColor();
        int[] colors = {color1, color2};
        float[] positions = {0.0f, 1.0f};

        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{android.R.attr.state_enabled}}, colors);
        ViewCompat.setBackgroundTintList(myview, colorStateList);
        ViewCompat.setBackgroundTintMode(myview, PorterDuff.Mode.MULTIPLY);
    }

    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(105, random.nextInt(100), random.nextInt(100), random.nextInt(100));
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lap,counts,Name,accountnum,Accountnum;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lap = itemView.findViewById(R.id.lap);
            counts = itemView.findViewById(R.id.counts);
            Name = itemView.findViewById(R.id.Name);
            Accountnum = itemView.findViewById(R.id.Accountnum);
            accountnum = itemView.findViewById(R.id.accountnum);


        }



    }

}

