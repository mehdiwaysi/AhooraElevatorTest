package com.snayab.ahooraelevator.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;


public class App extends Application {
    private static App instance;

    public  App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
//        PushPole.initialize(this, true);
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
