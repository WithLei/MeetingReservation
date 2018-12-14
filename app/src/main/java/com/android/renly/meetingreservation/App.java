package com.android.renly.meetingreservation;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initConfig();
    }

    private void initConfig() {

    }

    public static Context getContext() {
        return context;
    }

    public static final String MY_SP_NAME = "MeetingReservation";
}
