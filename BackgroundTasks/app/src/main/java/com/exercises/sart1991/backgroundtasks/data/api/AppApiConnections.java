package com.exercises.sart1991.backgroundtasks.data.api;

import com.exercises.sart1991.backgroundtasks.data.api.person.ApiPersonConnection;
import com.exercises.sart1991.backgroundtasks.data.api.person.AppApiPersonConnection;

/**
 * Created by sart1 on 5/31/2017.
 */

public class AppApiConnections implements ApiConnections {

    private ApiPersonConnection personConnection = new AppApiPersonConnection();

    @Override
    public String getPeople() {
        return personConnection.getPeople();
    }
}
