package com.exercises.sart1991.backgroundtasks.ui.activity.json;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.exercises.sart1991.backgroundtasks.ui.base.BasePresenter;
import com.exercises.sart1991.backgroundtasks.util.json.JsonUtils;

import org.json.JSONArray;

/**
 * Created by sart1 on 6/1/2017.
 */

public class JsonPresenter<V extends JsonMvpView>
        extends BasePresenter<V> implements JsonMvpPresenter<V> {

    private static final String TAG = JsonPresenter.class.getSimpleName();

    @Override
    public void loadUserCards() {
        Log.i(TAG, "loadUserCards: ");
        getDataManager().getJsonArrayUsers(
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "onResponse: JSONArray: " + response);
                        getMvpView().setUserCards(
                                JsonUtils.deserializeJsonUserArray(response)
                        );
                        Log.i(TAG, "onResponse: Users: " + JsonUtils.deserializeJsonUserArray(response));
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
