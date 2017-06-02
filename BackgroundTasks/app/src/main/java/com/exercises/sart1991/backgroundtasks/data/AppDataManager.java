package com.exercises.sart1991.backgroundtasks.data;

import android.content.Context;

import com.android.volley.Response;
import com.exercises.sart1991.backgroundtasks.data.api.ApiConnections;
import com.exercises.sart1991.backgroundtasks.data.api.AppApiConnections;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sart1 on 5/30/2017.
 */

public class AppDataManager implements  DataManager {

    private ApiConnections apiConnections;

    public AppDataManager(Context context) {
         apiConnections = new AppApiConnections(context);
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

    @Override
    public void putUser(User user) {
        apiConnections.putUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        apiConnections.deleteUser(userId);
    }

    @Override
    public void getVolleyUsers(Response.Listener<String> listener,
                               Response.ErrorListener errorListener) {
        apiConnections.getVolleyUsers(listener, errorListener);
    }

    interface Callback {
        void userDataModified();
    }

    @Override
    public void postVolleyUser(JSONObject jsonObject,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener) {
        apiConnections.postVolleyUser(jsonObject, listener, errorListener);
    }

    @Override
    public void putVolleyUser(JSONObject jsonObject,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener) {

        apiConnections.putVolleyUser(jsonObject, listener, errorListener);
    }

    @Override
    public void deleteVolleyUser(int id,
                                 Response.Listener<String> listener,
                                 Response.ErrorListener errorListener) {

        apiConnections.deleteVolleyUser(id, listener, errorListener);
    }

    @Override
    public void getJsonArrayUsers(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        apiConnections.getJsonArrayUsers(listener, errorListener);
    }
}
