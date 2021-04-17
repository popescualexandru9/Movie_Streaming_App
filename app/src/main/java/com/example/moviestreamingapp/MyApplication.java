package com.example.moviestreamingapp;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {

    private static com.example.moviestreamingapp.MyApplication mInstance;
    private static Context mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                Context.class, "moviesDb").build();
    }

    public static com.example.moviestreamingapp.MyApplication getInstance(){
        return mInstance;
    }

    public static Context getAppDatabase(){
        return mAppDatabase;
    }
}
