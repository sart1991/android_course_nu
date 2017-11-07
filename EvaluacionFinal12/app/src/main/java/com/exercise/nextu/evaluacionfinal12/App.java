package com.exercise.nextu.evaluacionfinal12;

import android.app.Application;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class App extends Application {

    public static App instance;

    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
