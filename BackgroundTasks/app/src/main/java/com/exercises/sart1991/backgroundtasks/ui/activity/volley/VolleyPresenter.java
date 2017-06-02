package com.exercises.sart1991.backgroundtasks.ui.activity.volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.exercises.sart1991.backgroundtasks.ui.base.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sart1 on 6/1/2017.
 */

public class VolleyPresenter<V extends VolleyMvpView>
        extends BasePresenter<V> implements VolleyMvpPresenter<V> {

    @Override
    public void clickButtonGet() {
        getDataManager().getVolleyUsers(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getMvpView().showResult(response);
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
    public void clickButtonPost() {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject()
                    .put("username", getMvpView().getUsername())
                    .put("email", getMvpView().getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getDataManager().postVolleyUser(
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        getMvpView().showResult(response.toString());
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
    public void clickButtonPut() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject()
                    .put("id", Integer.valueOf(getMvpView().getId()))
                    .put("username", getMvpView().getUsername())
                    .put("email", getMvpView().getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getDataManager().putVolleyUser(
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        getMvpView().showResult(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getMvpView().onError(error.toString(), null);
                    }
                }
        );
    }

    @Override
    public void clickButtonDelete() {
        getDataManager().deleteVolleyUser(
                Integer.valueOf(getMvpView().getId()),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getMvpView().showResult(response);
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
