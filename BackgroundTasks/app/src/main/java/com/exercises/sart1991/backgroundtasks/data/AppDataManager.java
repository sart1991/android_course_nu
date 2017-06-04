package com.exercises.sart1991.backgroundtasks.data;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.exercises.sart1991.backgroundtasks.data.api.ApiConnections;
import com.exercises.sart1991.backgroundtasks.data.api.AppApiConnections;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;
import com.exercises.sart1991.backgroundtasks.data.provider.AppDataProvider;
import com.exercises.sart1991.backgroundtasks.data.provider.DataProviderHelper;
import com.exercises.sart1991.backgroundtasks.data.provider.LoaderProvider;
import com.exercises.sart1991.backgroundtasks.data.provider.ProviderContainer;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sart1 on 5/30/2017.
 */

public class AppDataManager implements DataManager {

    private ApiConnections apiConnections;
    private DataProviderHelper dataProvider;

    public AppDataManager(Context context) {
        apiConnections = new AppApiConnections(context);
        dataProvider = new AppDataProvider(context);
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

    @Override
    public void cityWeather(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        apiConnections.cityWeather(listener, errorListener);
    }

    @Override
    public ImageLoader getImageLoader() {
        return apiConnections.getImageLoader();
    }

    @Override
    public String getImageUrl(String tag) {
        return apiConnections.getImageUrl(tag);
    }

    @Override
    public ProviderContainer getContentProvider() {
        return dataProvider.getContentProvider();
    }

    @Override
    public Uri insertProviderData(String data) {
        return dataProvider.insertProviderData(data);
    }

    @Override
    public LoaderProvider getLoaderData(LoaderProvider.Callback callback) {
        return dataProvider.getLoaderData(callback);
    }
}
