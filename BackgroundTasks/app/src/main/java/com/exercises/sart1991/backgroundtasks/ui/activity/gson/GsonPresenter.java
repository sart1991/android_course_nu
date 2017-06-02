package com.exercises.sart1991.backgroundtasks.ui.activity.gson;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.exercises.sart1991.backgroundtasks.ui.base.BasePresenter;
import com.exercises.sart1991.backgroundtasks.util.json.JsonUtils;

import org.json.JSONArray;

/**
 * Created by sart1 on 6/2/2017.
 */

public class GsonPresenter<V extends GsonMvpView>
        extends BasePresenter<V> implements GsonMvpPresenter<V> {

    @Override
    public void loadUsers() {
        getDataManager().getJsonArrayUsers(
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        getMvpView().setUsers(
                                JsonUtils.deserializeGsonJsonUserArray(response)
                        );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getMvpView().onError(error.toString(), null);
                    }
                }
        );
    }

    @Override
    public void loadUsersFromString() {
        getDataManager().getVolleyUsers(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getMvpView().setUsers(
                                JsonUtils.deserializeGsonJsonUserArrayFromStrin(response)
                        );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getMvpView().onError(error.toString(), null);
                    }
                }
        );
    }
}
