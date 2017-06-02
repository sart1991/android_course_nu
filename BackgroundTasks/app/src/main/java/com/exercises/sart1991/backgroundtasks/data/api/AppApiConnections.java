package com.exercises.sart1991.backgroundtasks.data.api;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.exercises.sart1991.backgroundtasks.data.api.person.ApiPersonConnection;
import com.exercises.sart1991.backgroundtasks.data.api.person.AppApiPersonConnection;
import com.exercises.sart1991.backgroundtasks.data.api.user.ApiUserConnection;
import com.exercises.sart1991.backgroundtasks.data.api.user.AppApiUserConnection;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sart1 on 5/31/2017.
 */

public class AppApiConnections implements ApiConnections {

    private ApiPersonConnection personConnection;
    private ApiUserConnection userConnection;

    public AppApiConnections(Context context) {
        personConnection = new AppApiPersonConnection();
        userConnection = new AppApiUserConnection(context);
    }

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

    @Override
    public void getVolleyUsers(Response.Listener<String> listener,
                               Response.ErrorListener errorListener) {
        userConnection.getVolleyUsers(listener, errorListener);
    }

    @Override
    public void postVolleyUser(JSONObject jsonObject,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener) {
        userConnection.postVolleyUser(jsonObject, listener, errorListener);
    }

    @Override
    public void putVolleyUser(JSONObject jsonObject,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener) {

        userConnection.putVolleyUser(jsonObject, listener, errorListener);
    }

    @Override
    public void deleteVolleyUser(int id,
                                 Response.Listener<String> listener,
                                 Response.ErrorListener errorListener) {

        userConnection.deleteVolleyUser(id, listener, errorListener);
    }

    @Override
    public void getJsonArrayUsers(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        userConnection.getJsonArrayUsers(listener, errorListener);
    }
}
