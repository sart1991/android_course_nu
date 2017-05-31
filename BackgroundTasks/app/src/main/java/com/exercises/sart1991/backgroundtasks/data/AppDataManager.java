package com.exercises.sart1991.backgroundtasks.data;

import android.content.Context;

import com.exercises.sart1991.backgroundtasks.data.api.ApiConnections;
import com.exercises.sart1991.backgroundtasks.data.api.AppApiConnections;

/**
 * Created by sart1 on 5/30/2017.
 */

public class AppDataManager implements  DataManager {

    private ApiConnections apiConnections = new AppApiConnections();

    public AppDataManager(Context context) {

    }

    @Override
    public String getPeople() {
        return apiConnections.getPeople();
    }
}
