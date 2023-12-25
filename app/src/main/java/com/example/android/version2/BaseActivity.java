package com.example.android.version2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String THEME_PREF = "theme_pref";
    private static final String THEME_KEY = "theme_key";
    private static final String THEME = "theme";
    public static final String title = "title";
    public static final String countpref = "count";
    public static final String limitpref = "limit";
    private boolean isDarkThemeEnabled=true;
    private String themecolor="light";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        isDarkThemeEnabled = sharedPreferences.getBoolean(THEME_KEY, "light");
        themecolor = sharedPreferences.getString(THEME, "light");

//        if (isDarkThemeEnabled) {
//            setTheme(R.style.AppTheme_NoActionBar_light);
//        } else {
//            setTheme(R.style.AppTheme_NoActionBar_dark);
//        }

        if (themecolor.equals("light")) {
            setTheme(R.style.AppTheme_NoActionBar_light);
        }else if (themecolor.equals("")) {
            setTheme(R.style.AppTheme_NoActionBar_light);
        } else if(themecolor.equals("dark")) {
            setTheme(R.style.AppTheme_NoActionBar_dark);
        }else if(themecolor.equals("blue")) {
            setTheme(R.style.AppTheme_NoActionBar_blue);
        }else if(themecolor.equals("red")) {
            setTheme(R.style.AppTheme_NoActionBar_red);
        }else if(themecolor.equals("green")) {
            setTheme(R.style.AppTheme_NoActionBar_green);
        }
    }

    public void setAppTheme2(String themecolor) {
        this.themecolor = themecolor;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(!sharedPreferences.getString(THEME, "light").equals(themecolor)){
            editor.putString(THEME, themecolor);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), ColorActivity.class));
            recreate();
            finish();
        }



    }

}
