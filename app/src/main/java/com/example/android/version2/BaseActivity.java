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
    private boolean isDarkThemeEnabled=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isDarkThemeEnabled = sharedPreferences.getBoolean(THEME_KEY, false);

        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_NoActionBar_light);
        } else {
            setTheme(R.style.AppTheme_NoActionBar_dark);
        }
    }

    public void setAppTheme2(boolean isDarkThemeEnabled) {
        this.isDarkThemeEnabled = isDarkThemeEnabled;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean(THEME_KEY, false)!=isDarkThemeEnabled){
            editor.putBoolean(THEME_KEY, isDarkThemeEnabled);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), version2list.class));
            recreate();
        }



    }

}
