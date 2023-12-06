package com.example.android.version2;

import com.example.android.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.os.LocaleListCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
//import com.example.tasbiix.NotificationReceiver;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {
    static datedata myDB;
    static TextView textViewCount,t1,t2,t3,t4,titles;
    ImageView menus,update,resets,add;
    private static int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewCount = findViewById(R.id.textViewCount);
        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        t4 = findViewById(R.id.textView4);
        myDB = new datedata(MainActivity.this);
        resets = findViewById(R.id.resets);
        menus = findViewById(R.id.menus);
        titles = findViewById(R.id.titles);
        update = findViewById(R.id.update);
        add = findViewById(R.id.add);

//        start();
//        scheduleNotification();
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                start();
////                sendNotification ("if this works i will be very happy", "yaa allaah" );
//                // Get the time at which you want to send the notification
//                startActivity(new Intent(getApplicationContext(), datalist.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });

        menus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, menus);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_setting, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.routine) {
                            startActivity(new Intent(getApplicationContext(), version2list.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            return true;
                        } else if (itemId == R.id.history) {
                            startActivity(new Intent(getApplicationContext(), datalist.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            return true;
                        }else if (itemId == R.id.setting) {
                            startActivity(new Intent(getApplicationContext(), ColorActivity.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.popupdate);

                TextView text = (TextView) dialog.findViewById(R.id.textView);
                text.setText("count");

                TextInputEditText  updatecount = (TextInputEditText) dialog.findViewById(R.id.count);
                updatecount.setText(String.valueOf(count));

                Button save = (Button) dialog.findViewById(R.id.save);
                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count= Integer.parseInt(updatecount.getText().toString());
                        updateCount();
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


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementCount();
            }

        });
/// make drawable xml shape from android

        resets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();

                // Create a date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                String formattedDate = dateFormat.format(currentDate);
                myDB.addData(String.valueOf(count),formattedDate,"reset",titles.getText().toString());
                count=0;
                @SuppressLint("DefaultLocale") String countString = String.format("%05d", count);
                textViewCount.setText(String.valueOf(countString.charAt(4)));
                t1.setText(String.valueOf(countString.charAt(3)));
                t2.setText(String.valueOf(countString.charAt(2)));
                t3.setText(String.valueOf(countString.charAt(1)));
                t4.setText(String.valueOf(countString.charAt(0)));
            }

        });
    }
    private void incrementCount() {
        count++;

        if(count>=100000){
            Date currentDate = new Date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            String formattedDate = dateFormat.format(currentDate);
            myDB.addData(String.valueOf(count),formattedDate,"congrats",titles.getText().toString());
            count=0;
            @SuppressLint("DefaultLocale") String countString = String.format("%05d", count);
            textViewCount.setText(String.valueOf(countString.charAt(4)));
            t1.setText(String.valueOf(countString.charAt(3)));
            t2.setText(String.valueOf(countString.charAt(2)));
            t3.setText(String.valueOf(countString.charAt(1)));
            t4.setText(String.valueOf(countString.charAt(0)));
        }
        else{
            @SuppressLint("DefaultLocale") String countString = String.format("%05d", count);
            textViewCount.setText(String.valueOf(countString.charAt(4)));
            t1.setText(String.valueOf(countString.charAt(3)));
            t2.setText(String.valueOf(countString.charAt(2)));
            t3.setText(String.valueOf(countString.charAt(1)));
            t4.setText(String.valueOf(countString.charAt(0)));
        }

    }
    private void updateCount() {
        if(count>99999){
            count=99999;
        }
        @SuppressLint("DefaultLocale") String countString = String.format("%05d", count);
        textViewCount.setText(String.valueOf(countString.charAt(4)));
        t1.setText(String.valueOf(countString.charAt(3)));
        t2.setText(String.valueOf(countString.charAt(2)));
        t3.setText(String.valueOf(countString.charAt(1)));
        t4.setText(String.valueOf(countString.charAt(0)));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            incrementCount();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            incrementCount();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_POWER) {
            incrementCount();
            return true;
        }

        // If the key event is not volume up or volume down, let the system handle it
        return super.onKeyDown(keyCode, event);
    }

    public static void restcount() {
        Date currentDate = new Date();

        // Create a date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);
        myDB.addData(String.valueOf(count),formattedDate,"24h reset",formattedDate);
        count=0;
        @SuppressLint("DefaultLocale") String countString = String.format("%05d", count);
        textViewCount.setText(String.valueOf(countString.charAt(4)));
        t1.setText(String.valueOf(countString.charAt(3)));
        t2.setText(String.valueOf(countString.charAt(2)));
        t3.setText(String.valueOf(countString.charAt(1)));
        t4.setText(String.valueOf(countString.charAt(0)));
    }




}