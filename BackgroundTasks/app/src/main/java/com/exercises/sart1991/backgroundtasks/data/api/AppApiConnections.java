package com.exercises.sart1991.backgroundtasks.data.api;

import com.exercises.sart1991.backgroundtasks.data.api.person.ApiPersonConnection;
import com.exercises.sart1991.backgroundtasks.data.api.person.AppApiPersonConnection;
import com.exercises.sart1991.backgroundtasks.data.api.user.ApiUserConnection;
import com.exercises.sart1991.backgroundtasks.data.api.user.AppApiUserConnection;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

/**
 * Created by sart1 on 5/31/2017.
 */

public class AppApiConnections implements ApiConnections {

    private ApiPersonConnection personConnection = new AppApiPersonConnection();
    private ApiUserConnection userConnection = new AppApiUserConnection();

    @Override
    public String getPeople() {
        return personConnection.getPeople();
    }

    @Override
    public String getUsers() {
        return userConnection.getUsers();
    }

    @Override
    public void postUser(User user) {
        userConnection.postUser(user);
    }

    @Override
    public void putUser(User user) {
        userConnection.putUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userConnection.deleteUser(userId);
    }
}
