package com.example.android.version2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class version2list extends BaseActivity {

    ImageButton back,add,sort;
    routinedata myDB;
    RoutineAdapter Adapter;
    RecyclerView recyclerView;
//    ImageView back;
    ArrayList<String> lap,counts,Name;
    SearchView searchView;
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

        searchView = findViewById(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        back = findViewById(R.id.back);
        sort = findViewById(R.id.sort);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ColorActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(version2list.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_routine2);

                TextView text = (TextView) dialog.findViewById(R.id.accountnum2);
                TextView text2 = (TextView) dialog.findViewById(R.id.Accountnum);
                TextView text3 = (TextView) dialog.findViewById(R.id.accountnum);
                TextInputEditText title = (TextInputEditText) dialog.findViewById(R.id.title);
                TextInputEditText laps = (TextInputEditText) dialog.findViewById(R.id.lap);
                TextInputEditText count = (TextInputEditText) dialog.findViewById(R.id.Count);
                text.setText("Title");
                text2.setText("Count");
                text3.setText("Lap");
                title.setHint("enter title");
                count.setHint("enter count");
                laps.setHint("enter lap");

                Button save = (Button) dialog.findViewById(R.id.save);
                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDB.addData(count.getText().toString(),laps.getText().toString(),title.getText().toString());
                        lap = new ArrayList<>();
                        counts = new ArrayList<>();
                        Name = new ArrayList<>();
                        storeDataInArrays();
                        getData();
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(version2list.this, sort);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.asc) {
                            lap = new ArrayList<>();
                            counts = new ArrayList<>();
                            Name = new ArrayList<>();
                            storeDataInArraysorder("ASC");
                            getData();

                            return true;
                        } else if (itemId == R.id.desc) {
                            lap = new ArrayList<>();
                            counts = new ArrayList<>();
                            Name = new ArrayList<>();
                            storeDataInArraysorder("DESC");
                            getData();
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lap = new ArrayList<>();
                counts = new ArrayList<>();
                Name = new ArrayList<>();
                storeArraysearch(query);
                getData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lap = new ArrayList<>();
                counts = new ArrayList<>();
                Name = new ArrayList<>();
                storeArraysearch(newText);
                getData();
                return true;
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
                counts.add(cursor.getString(3));
                lap.add(cursor.getString(1));
                Name.add(cursor.getString(2));
            }

        }


    }

    void storeArraysearch(String text){

        Cursor cursor = myDB.readsearch(text);
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                counts.add(cursor.getString(3));
                lap.add(cursor.getString(1));
                Name.add(cursor.getString(2));
            }

        }


    }

    void storeDataInArraysorder(String value){

        Cursor cursor = myDB.readAllData_inorder(value);
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                counts.add(cursor.getString(3));
                lap.add(cursor.getString(1));
                Name.add(cursor.getString(2));
            }

        }


    }
}