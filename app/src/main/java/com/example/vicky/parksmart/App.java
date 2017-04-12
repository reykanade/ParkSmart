package com.example.vicky.parksmart;

import android.app.Application;
import android.content.Context;

/**
 * Created by vicky on 08/04/2017.
 */

public class App extends Application {
    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}