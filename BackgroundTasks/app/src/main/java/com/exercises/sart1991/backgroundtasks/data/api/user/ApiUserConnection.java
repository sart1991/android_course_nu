package com.exercises.sart1991.backgroundtasks.data.api.user;

import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

/**
 * Created by sart1 on 5/31/2017.
 */

public interface ApiUserConnection {
    String getUsers();
    void postUser(User user);
    void putUser(User user);
    void deleteUser(int userId);
}
