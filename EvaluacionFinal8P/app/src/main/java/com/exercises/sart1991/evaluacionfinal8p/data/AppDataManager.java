package com.exercises.sart1991.evaluacionfinal8p.data;

import android.content.Context;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.AppApiSchool;

/**
 * Created by sart1 on 6/7/2017.
 */

public class AppDataManager implements DataManager {

    private static AppDataManager dataManager;

    private ApiSchoolHelper apiSchoolHelper;

    private AppDataManager() {
        apiSchoolHelper = new AppApiSchool();
    }

    public static AppDataManager getInstance() {
        if (dataManager == null) {
            dataManager = new AppDataManager();
        }
        return dataManager;
    }

    @Override
    public void provideApiSchoolContext(Context context) {
        apiSchoolHelper.provideApiSchoolContext(context);
    }
}
