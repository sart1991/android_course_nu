package com.exercises.sart1991.evaluacionfinal8p.data;

import android.content.Context;

/**
 * Created by sart1 on 6/7/2017.
 */

public class AppDataManager implements DataManager{

    private static AppDataManager dataManager;

    private AppDataManager() {}

    public static AppDataManager getInstance() {
        if (dataManager == null) {
            dataManager = new AppDataManager();
        }
        return dataManager;
    }
}
