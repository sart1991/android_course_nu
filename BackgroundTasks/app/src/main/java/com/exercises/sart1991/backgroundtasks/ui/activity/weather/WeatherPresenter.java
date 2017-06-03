package com.exercises.sart1991.backgroundtasks.ui.activity.weather;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.exercises.sart1991.backgroundtasks.ui.base.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sart1 on 6/2/2017.
 */

public class WeatherPresenter<V extends WeatherMvpView>
        extends BasePresenter<V> implements WeatherMvpPresenter<V> {

    @Override
    public void loadWeatherData() {
        getDataManager().cityWeather(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        deserializeWeatherData(response);
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

    private void deserializeWeatherData(JSONObject object) {
        try {
            String url = getDataManager().getImageUrl(
                    object.getJSONArray("weather").getJSONObject(0).getString("icon")
            );
            getMvpView().loadImageWeather(
                    url, getDataManager().getImageLoader()
            );
            getMvpView().setWeatherMain(
                    object.getJSONArray("weather").getJSONObject(0).getString("main")
            );
            getMvpView().setWeatherDescription(
                object.getJSONArray("weather").getJSONObject(0).getString("description")
            );
            getMvpView().setTemperature(
                    object.getJSONObject("main").getDouble("temp") + "ºK"
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
