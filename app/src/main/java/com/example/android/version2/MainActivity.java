package com.example.android.version2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends BaseActivity {

    Button back,back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back = findViewById(R.id.button);
        back2 = findViewById(R.id.button2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((App) getApplication()).setAppTheme(true);
                setAppTheme2(true);
                // Set the content view after changing the theme
//                setContentView(R.layout.activity_main);
//                ((App) getApplication()).setAppTheme(true); // Switch to dark theme
//                ((App) getApplication()).setAppTheme(false); // Switch to light theme
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme2(false);

                // Set the content view after changing the theme
//                setContentView(R.layout.activity_main);
            }
        });
    }

}