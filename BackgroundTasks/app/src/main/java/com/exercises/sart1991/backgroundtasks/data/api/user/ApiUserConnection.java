package com.exercises.sart1991.backgroundtasks.data.api.user;

import com.android.volley.Response;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

import org.json.JSONObject;

/**
 * Created by sart1 on 5/31/2017.
 */

public interface ApiUserConnection {
    String getUsers();
    void postUser(User user);
    void putUser(User user);
    void deleteUser(int userId);
    void getVolleyUsers(Response.Listener<String> listener, Response.ErrorListener errorListener);
    void postVolleyUser(
            JSONObject jsonObject,
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener
    );
    void putVolleyUser(
            JSONObject jsonObject,
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener
    );
    void deleteVolleyUser(
            int id,
            Response.Listener<String> listener,
            Response.ErrorListener errorListener
    );
}
