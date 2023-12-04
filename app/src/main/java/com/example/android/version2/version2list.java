package com.example.android.version2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class version2list extends BaseActivity {

    ImageButton back,imageButton;
    routinedata myDB;
    RoutineAdapter Adapter;
    RecyclerView recyclerView;
//    ImageView back;
    ArrayList<String> lap,counts,Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version2list);

        recyclerView = findViewById(R.id.rcyview);

        myDB = new routinedata(version2list.this);

        lap = new ArrayList<>();
        counts = new ArrayList<>();
        Name = new ArrayList<>();
        storeDataInArrays();
        getData();

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ColorActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(version2list.this, imageButton);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.profile) {
//                            startActivity(new Intent(getApplicationContext(), register.class));
//                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                            finish();
                            return true;
                        } else if (itemId == R.id.share) {
//                            showShareMenu();
                            return true;
                        }
                        // Add conditions for other menu items as needed
                        return false;

                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }

        });
    }

    private void getData() {

        Adapter = new RoutineAdapter(version2list.this,lap,counts,Name);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(version2list.this));
    }

    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            SQLiteDatabase db = myDB.getWritableDatabase();
            myDB.insertInitialData(db);
            db.close();
            storeDataInArrays();
        }else{
            while (cursor.moveToNext()){
                counts.add(cursor.getString(0));
                lap.add(cursor.getString(1));
                Name.add(cursor.getString(2));
            }

        }


    }
}