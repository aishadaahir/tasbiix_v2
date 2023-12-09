package com.example.android.version2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class ColorActivity extends BaseActivity {
    private static final String THEME_KEY = "theme_key";
    RadioButton radioButton1,radioButton2;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.light);
        radioButton2 = findViewById(R.id.dark);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDarkThemeEnabled = sharedPreferences.getBoolean(THEME_KEY, false);
        if (isDarkThemeEnabled) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
            radioButton.setChecked(true);

        } else {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(1);
            radioButton.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.light) {
                    setAppTheme2(true);
                } else if (checkedId == R.id.dark) {
                    setAppTheme2(false);
                }
            }
        });

    }

}