package com.example.android.version2;

import android.app.Application;

public class App extends Application {
    private boolean isDarkThemeEnabled = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // Set the initial theme based on your requirements
        setAppTheme(isDarkThemeEnabled);
    }

    public void setAppTheme(boolean isDarkThemeEnabled) {
        this.isDarkThemeEnabled = isDarkThemeEnabled;

        if (isDarkThemeEnabled) {
            setTheme(R.style.AppTheme_NoActionBar);
        } else {
            setTheme(R.style.AppTheme_blue_NoActionBar);
        }
    }
}