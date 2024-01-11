package com.example.android.version2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.PopupMenu;
import android.widget.SearchView;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class datalist extends BaseActivity {

    datedata myDB;
    dataAdapter Adapter;
    RecyclerView  recyclerView;
    ImageView back,sort;
    ArrayList<String> title,count,date,type;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist);

        recyclerView = findViewById(R.id.rcyview);

        myDB = new datedata(datalist.this);

        title = new ArrayList<>();
        count = new ArrayList<>();
        date = new ArrayList<>();
        type = new ArrayList<>();
        storeDataInArrays();
        getData();
        searchView = findViewById(R.id.search);
        sort = findViewById(R.id.sort);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                title = new ArrayList<>();
                count = new ArrayList<>();
                date = new ArrayList<>();
                type = new ArrayList<>();
                storeArraysearch(query);
                getData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                title = new ArrayList<>();
                count = new ArrayList<>();
                date = new ArrayList<>();
                type = new ArrayList<>();
                storeArraysearch(newText);
                getData();
                return true;
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(datalist.this, sort);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.asc) {
                            title = new ArrayList<>();
                            count = new ArrayList<>();
                            date = new ArrayList<>();
                            type = new ArrayList<>();
                            storeDataInArraysorder("ASC");
                            getData();

                            return true;
                        } else if (itemId == R.id.desc) {
                            title = new ArrayList<>();
                            count = new ArrayList<>();
                            date = new ArrayList<>();
                            type = new ArrayList<>();
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

    }



    private void getData() {

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        // Get yesterday's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();

        // Get yesterday's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date beforeyesterday = calendar.getTime();

        // Get last week's date
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        Date lastWeek = calendar.getTime();

        // Get last week's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date beforelastWeek = calendar.getTime();

        // Get last month's date
        calendar.add(Calendar.MONTH, -1);
        Date lastMonth = calendar.getTime();

        // Get last month's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date beforelastMonth = calendar.getTime();

        // Get last year's date
        calendar.add(Calendar.YEAR, -1);
        Date lastYear = calendar.getTime();

        // Format the dates as strings
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String todayString = sdf.format(today);
        String yesterdayString = sdf.format(yesterday);
        String beforeyesterdayString = sdf.format(beforeyesterday);
        String lastWeekString = sdf.format(lastWeek);
        String beforelastWeekString = sdf.format(beforelastWeek);
        String lastMonthString = sdf.format(lastMonth);
        String beforelastMonthString = sdf.format(beforelastMonth);
        String lastYearString = sdf.format(lastYear);

        // Print the dates
        Log.e("Today: ", todayString);
        Log.e("Yesterday: ", yesterdayString);
        Log.e("beforeyesterday: ", beforeyesterdayString);
        Log.e("Last Week: ", lastWeekString);
        Log.e("befoteLast Week: ", beforelastWeekString);
        Log.e("Last Month: ",lastMonthString);
        Log.e("beforeLast Month: ",beforelastMonthString);
        Log.e("Last Year: ",lastYearString);

        Adapter = new dataAdapter(datalist.this,title,count,date,type,todayString);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(datalist.this));
    }

    void storeDataInArrays(){

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                count.add(cursor.getString(0));
                date.add(cursor.getString(1));
                type.add(cursor.getString(2));
                title.add(cursor.getString(3));
            }

        }
    }

    void storeArraysearch(String text){

        Cursor cursor = myDB.readsearch(text);
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                count.add(cursor.getString(0));
                date.add(cursor.getString(1));
                type.add(cursor.getString(2));
                title.add(cursor.getString(3));
            }

        }
    }

    void storeDataInArraysorder(String value){

        Cursor cursor = myDB.readAllData_inorder(value);
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                count.add(cursor.getString(0));
                date.add(cursor.getString(1));
                type.add(cursor.getString(2));
                title.add(cursor.getString(3));
            }

        }
    }
}