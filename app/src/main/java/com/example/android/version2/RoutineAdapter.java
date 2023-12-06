package com.example.android.version2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id,lap,counts,Name;

    ItemClicklistner ItemClicklistner;
    public RoutineAdapter(Context context, ArrayList id, ArrayList lap, ArrayList counts, ArrayList Name, ItemClicklistner ItemClicklistner){
        this.context = context;
        this.id = id;
        this.lap = lap;
        this.counts = counts;
        this.Name = Name;
        this.ItemClicklistner = ItemClicklistner;

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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, version2list.class);
                intent.putExtra("ID", String.valueOf(id.get(position)));

                ItemClicklistner.onItem(position,intent);
            }
        });

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

    public interface ItemClicklistner {
        void onItem(int position,Intent intent);
    }
    @Override
    public int getItemCount() {
        return id.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lap,counts,Name,accountnum,Accountnum;
        ImageView delete;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lap = itemView.findViewById(R.id.lap);
            counts = itemView.findViewById(R.id.counts);
            Name = itemView.findViewById(R.id.Name);
            Accountnum = itemView.findViewById(R.id.Accountnum);
            accountnum = itemView.findViewById(R.id.accountnum);
            delete = itemView.findViewById(R.id.delete);


        }



    }

}

