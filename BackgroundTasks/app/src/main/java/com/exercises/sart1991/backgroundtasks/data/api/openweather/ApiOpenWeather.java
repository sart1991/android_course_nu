package com.exercises.sart1991.backgroundtasks.data.api.openweather;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONObject;

/**
 * Created by sart1 on 6/2/2017.
 */

public interface ApiOpenWeather {
    void cityWeather(Response.Listener<JSONObject> listener,
                     Response.ErrorListener errorListener);

    ImageLoader getImageLoader();

    String getImageUrl(String tag);
}
