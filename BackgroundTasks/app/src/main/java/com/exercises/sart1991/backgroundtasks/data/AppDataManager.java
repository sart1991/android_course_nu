package com.exercises.sart1991.backgroundtasks.data;

import android.content.Context;

import com.exercises.sart1991.backgroundtasks.data.api.ApiConnections;
import com.exercises.sart1991.backgroundtasks.data.api.AppApiConnections;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

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

    @Override
    public String getUsers() {
        return apiConnections.getUsers();
    }

    @Override
    public void postUser(User user) {
        apiConnections.postUser(user);
    }

    interface Callback {
        void userDataModified();
    }
}
