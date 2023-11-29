package com.example.android.version2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class datalist extends AppCompatActivity {

    datedata myDB;
    dataAdapter Adapter;
    RecyclerView  recyclerView;
    ImageView back;
    ArrayList<String> name,phone,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist);

        recyclerView = findViewById(R.id.rcyview);

        myDB = new datedata(datalist.this);

        name = new ArrayList<>();
        phone = new ArrayList<>();
        type = new ArrayList<>();
        storeDataInArrays();
        getData();

        back = findViewById(R.id.tton33);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void getData() {

        Adapter = new dataAdapter(datalist.this,name,phone,type);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(datalist.this));
    }

    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                name.add(cursor.getString(0));
                phone.add(cursor.getString(1));
                type.add(cursor.getString(2));
            }

        }


    }
}