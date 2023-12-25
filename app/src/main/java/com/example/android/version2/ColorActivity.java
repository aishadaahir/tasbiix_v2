package com.example.android.version2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class ColorActivity extends BaseActivity {
    private static final String THEME_KEY = "theme_key";
    private static final String THEME = "theme";
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;
    RadioGroup radioGroup;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.light);
        radioButton2 = findViewById(R.id.dark);
        radioButton3 = findViewById(R.id.blue);
        radioButton4 = findViewById(R.id.red);
        radioButton5 = findViewById(R.id.green);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themecolor = sharedPreferences.getString(THEME, "light");
        if (themecolor.equals("light")) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
            radioButton.setChecked(true);
        } else if(themecolor.equals("dark")) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(1);
            radioButton.setChecked(true);
        } else if(themecolor.equals("blue")) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(2);
            radioButton.setChecked(true);
        } else if(themecolor.equals("red")) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(3);
            radioButton.setChecked(true);
        }else if(themecolor.equals("green")) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(4);
            radioButton.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.light) {
                    setAppTheme2("light");
                } else if (checkedId == R.id.dark) {
                    setAppTheme2("dark");
                }else if (checkedId == R.id.blue) {
                    setAppTheme2("blue");
                }else if (checkedId == R.id.red) {
                    setAppTheme2("red");
                }else if (checkedId == R.id.green) {
                    setAppTheme2("green");
                }
            }
        });

    }

}