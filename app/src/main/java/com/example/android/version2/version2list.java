package com.example.android.version2;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class version2list extends BaseActivity {

    ImageButton back,add,sort;
    routinedata myDB;
    RoutineAdapter Adapter;
    RecyclerView recyclerView;
//    ImageView back;
    ArrayList<String> id,lap,counts,Name;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version2list);

        recyclerView = findViewById(R.id.rcyview);

        myDB = new routinedata(version2list.this);

        id = new ArrayList<>();
        lap = new ArrayList<>();
        counts = new ArrayList<>();
        Name = new ArrayList<>();
        storeDataInArrays();
        getData();
        searchView = findViewById(R.id.search);


        back = findViewById(R.id.back);
        sort = findViewById(R.id.sort);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                        id = new ArrayList<>();
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
                            id = new ArrayList<>();
                            lap = new ArrayList<>();
                            counts = new ArrayList<>();
                            Name = new ArrayList<>();
                            storeDataInArraysorder("ASC");
                            getData();

                            return true;
                        } else if (itemId == R.id.desc) {
                            id = new ArrayList<>();
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
                id = new ArrayList<>();
                lap = new ArrayList<>();
                counts = new ArrayList<>();
                Name = new ArrayList<>();
                storeArraysearch(query);
                getData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                id = new ArrayList<>();
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

        Adapter = new RoutineAdapter(version2list.this,id,lap,counts,Name,new RoutineAdapter.ItemClicklistner() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItem(int position, Intent intent) {
                String ID = intent.getExtras().getString("ID");
                myDB.deleteRoutine(ID);
                Toast.makeText(version2list.this, "Routine removed from list", Toast.LENGTH_SHORT).show();
                Adapter.notifyDataSetChanged();
                id = new ArrayList<>();
                lap = new ArrayList<>();
                counts = new ArrayList<>();
                Name = new ArrayList<>();
                storeDataInArrays();
                getData();


            }
        },new RoutineAdapter.ItemClicklistner2() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItem(int position, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(version2list.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int sum= Integer.valueOf(intent.getExtras().getString("count"))*Integer.valueOf(intent.getExtras().getString("lap"));
                Log.e("errreee", String.valueOf(sum));
                Log.e("errreee", String.valueOf(intent.getExtras().getInt("count")));
                Log.e("errreee", intent.getExtras().getString("count"));
                editor.putString(title, intent.getExtras().getString("Title"));
                editor.putString(countpref, String.valueOf(0));
                editor.putString(limitpref, String.valueOf(sum));
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                String ID = intent.getExtras().getString("ID");
//                myDB.deleteRoutine(ID);
//                Toast.makeText(version2list.this, "Routine removed from list", Toast.LENGTH_SHORT).show();
//                Adapter.notifyDataSetChanged();
//                id = new ArrayList<>();
//                lap = new ArrayList<>();
//                counts = new ArrayList<>();
//                Name = new ArrayList<>();
//                storeDataInArrays();
//                getData();


            }

        });
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(version2list.this));
    }

    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            SQLiteDatabase db = myDB.getWritableDatabase();
            myDB.insertInitialDatas(db);
            db.close();
            storeDataInArrays();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
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
                id.add(cursor.getString(0));
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
                id.add(cursor.getString(0));
                counts.add(cursor.getString(3));
                lap.add(cursor.getString(1));
                Name.add(cursor.getString(2));
            }

        }


    }
}