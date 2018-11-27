package com.example.mahmoudafifi.simplealbum.application;


import android.app.Application;
import android.content.Context;

public class AlbumListApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        AlbumListApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
