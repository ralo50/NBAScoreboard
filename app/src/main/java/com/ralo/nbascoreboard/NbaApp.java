package com.ralo.nbascoreboard;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Luka on 26.03.2018.
 */

public class NbaApp extends Application {

    private static Context appContext;
    private static AppCompatActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static void setCurrentActivity(AppCompatActivity currentActivity){
        NbaApp.currentActivity = currentActivity;
    }

    public static AppCompatActivity getCurrentActivity(){
        return currentActivity;
    }

    public static Context getInstance(){
        return appContext;
    }

    public static FragmentManager getFragmentSupportManager() {
        return currentActivity.getSupportFragmentManager();
    }
}