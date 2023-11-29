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

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name,phone,type;


    public dataAdapter(Context context,ArrayList name,ArrayList phone,ArrayList type){
        this.context = context;
        this.name = name;
        this.phone = phone;
        this.type = type;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.datalaout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.phone.setText(String.valueOf(phone.get(position)));
        holder.type.setText(String.valueOf(type.get(position)));

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
        return name.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,phone,type;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.count);
            phone = itemView.findViewById(R.id.date);
            type = itemView.findViewById(R.id.date2);


        }



    }

}

