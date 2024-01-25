package com.example.android.version2;

import android.annotation.SuppressLint;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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

        // Get last month's date
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date longer = calendar.getTime();

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
        String longerString = sdf.format(longer);

        // Print the dates
        Log.e("Today: ", todayString);
        Log.e("Yesterday: ", yesterdayString);
        Log.e("beforeyesterday: ", beforeyesterdayString);
        Log.e("Last Week: ", lastWeekString);
        Log.e("befoteLast Week: ", beforelastWeekString);
        Log.e("Last Month: ",lastMonthString);
        Log.e("beforeLast Month: ",beforelastMonthString);
        Log.e("Last Year: ",lastYearString);
        Log.e("Longer: ",longerString);
        int i = 0;
        int countlastmonth=0;
        int positionlastmonth=-1;
        int countlastyear=0;
        int positionlastyear=-1;
        int countlastweek=0;
        int positionlastweek=-1;
        int countlasttoday=0;
        int positionlasttoday=-1;
        int countlastyesterday=0;
        int positionlastyesterday=-1;
        int countlonger=0;
        int positionlonger=-1;
        while(i<date.size()){
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE,dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date dates = inputFormat.parse(String.valueOf(date.get(i)));
                assert dates != null;
                String formattedDate = outputFormat.format(dates);
                Date datecompare = Format.parse(formattedDate);
                Date before = Format.parse(beforelastWeekString);
                Date after = Format.parse(lastMonthString);

//                Log.e("dateoutside", formattedDate);
                if (datecompare.after(after) && datecompare.before(before) || datecompare.equals(after)|| datecompare.equals(before)) {
//                    Log.e("dateoutside", formattedDate);
//                    Log.e("dateoutside", count.get(i));
                    if(positionlastmonth==-1){
                        positionlastmonth=i;
                    }
                    countlastmonth+= Integer.valueOf(count.get(i));
//                    Log.e("dateoutside", String.valueOf(countlastmonth));
                }else if (datecompare.after(Format.parse(lastYearString)) && datecompare.before(Format.parse(beforelastMonthString)) || datecompare.equals(Format.parse(beforelastMonthString))|| datecompare.equals(Format.parse(lastYearString))) {
//                    Log.e("dateoutside", formattedDate);
//                    Log.e("dateoutside", count.get(i));
                    if(positionlastyear==-1){
                        positionlastyear=i;
                    }
                    countlastyear+= Integer.valueOf(count.get(i));
//                    Log.e("dateoutside", String.valueOf(countlastyear));
                } else if (datecompare.after(Format.parse(lastWeekString)) && datecompare.before(Format.parse(beforeyesterdayString)) || datecompare.equals(Format.parse(beforeyesterdayString))|| datecompare.equals(Format.parse(lastWeekString))) {
//                    Log.e("dateoutside", formattedDate);
//                    Log.e("dateoutside", count.get(i));
                    if(positionlastweek==-1){
                        positionlastweek=i;
                    }
                    countlastweek+= Integer.valueOf(count.get(i));
//                    Log.e("dateoutside", String.valueOf(countlastweek));
                } else if (datecompare.equals(Format.parse(todayString))) {
//                    Log.e("dateoutside", formattedDate);
//                    Log.e("dateoutside", count.get(i));
                    if(positionlasttoday==-1){
                        positionlasttoday=i;
                    }
                    countlasttoday+= Integer.valueOf(count.get(i));
//                    Log.e("dateoutside", String.valueOf(countlasttoday));
                }else if (datecompare.equals(Format.parse(yesterdayString))) {
//                    Log.e("dateoutside", formattedDate);
//                    Log.e("dateoutside", count.get(i));
                    if(positionlastyesterday==-1){
                        positionlastyesterday=i;
                    }
                    countlastyesterday+= Integer.valueOf(count.get(i));
//                    Log.e("dateoutside", String.valueOf(countlastyesterday));
                } else if (datecompare.after(Format.parse(longerString))) {
//                    Log.e("dateoutside", formattedDate);
//                    Log.e("dateoutside", count.get(i));
                    if(positionlonger==-1){
                        positionlonger=i;
                    }
                    countlonger+= Integer.valueOf(count.get(i));
//                    Log.e("dateoutside", String.valueOf(countlonger));
                } else {
//                    Log.e("dateoutside", "outside if");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE,dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
//            @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            try {
//                Date dates = inputFormat.parse(String.valueOf(date.get(i)));
//                assert dates != null;
//                String formattedDate = outputFormat.format(dates);
//                SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
//                Date datecompare= Format.parse(formattedDate);
//                Date before= Format.parse(lastMonthString);
//                Date after= Format.parse(beforelastWeekString);
//                Log.e("dateoutside",formattedDate);
//                Log.e("date", String.valueOf(datecompare));
//                if(datecompare.after(after) && datecompare.before(before)){
////                    Log.e("dateoutside"," "+i+ ""+formattedDate);
//                    Log.e("dateoutside","insideif");
//                }
////            System.out.println("Formatted Date: " + formattedDate);
////                Log.e("dateformated",formattedDate);
//////            holder.passtime.setText(formattedDate);
////                if(Objects.equals(todayString, formattedDate)){
////                    if(pos==-1){
////                        pos=position;
////                        hold=holder;
////                    }
////                    if(position!=pos){
////                        holder.headlayout.setVisibility(View.GONE);
////                    }
////                    today=today+ Integer.parseInt((String) count.get(position));
////                    holder.passtime.setText(formattedDate);
////                    holder.changes(hold, position,today);
////                }
////                else if (Objects.equals(yesterday, formattedDate)) {
////                    if(posy==-1){
////                        posy=position;
////                        hold2=holder;
////                    }
////                    if(position!=posy){
////                        holder.headlayout.setVisibility(View.GONE);
////                    }
////                    yester=yester+ Integer.parseInt((String) count.get(position));
////                    holder.passtime.setText(formattedDate);
////                    holder.changes(hold2, position,yester);
////                }
////
////                else{
////                    Log.e("resdrvsretgsdf", String.valueOf(pos));
////                    Log.e("resdrvsretgsdf", String.valueOf(today));
////
//////                holder.changes(pos,today);
//////                holder.total.get(0).setText(today);
////                    holder.headlayout.setVisibility(View.GONE);
////                }
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Log.e("dateoutside",date.get(i));
            i++;
        }

        Log.e("month",""+countlastmonth+"  "+positionlastmonth);
        Log.e("year", ""+countlastyear+"  "+positionlastyear);
        Log.e("week", String.valueOf(countlastweek));
        Log.e("today", String.valueOf(countlasttoday));
        Log.e("yesterday", String.valueOf(countlastyesterday));
        Log.e("longer", ""+countlonger+"  "+positionlonger);
        Adapter = new dataAdapter(datalist.this,title,count,date,type,todayString,yesterdayString,countlastmonth,positionlastmonth
                ,countlastyear,positionlastyear,countlastweek,positionlastweek,countlasttoday,positionlasttoday,countlastyesterday,positionlastyesterday,countlonger,positionlonger);
        recyclerView.setAdapter(Adapter);
//        String newValue = "12" ;
//        int updateIndex = 0;
//        data.set(updateIndex, newValue);
//        Adapter.notifyItemChanged(updateIndex);
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