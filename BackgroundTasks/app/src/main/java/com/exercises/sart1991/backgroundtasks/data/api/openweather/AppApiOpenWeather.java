package com.exercises.sart1991.backgroundtasks.data.api.openweather;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.exercises.sart1991.backgroundtasks.data.api.user.AppApiUserConnection;

import org.json.JSONObject;

/**
 * Created by sart1 on 6/2/2017.
 */

public class AppApiOpenWeather implements ApiOpenWeather {

    private static final String TAG = AppApiUserConnection.class.getSimpleName();
    private static final String URL = "http://samples.openweathermap.org/data/2.5/weather?q=Tunja&appid=b1b15e88fa797225412429c1c50c122a1";

    private RequestQueue queue;

    public AppApiOpenWeather(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void cityWeather(Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        queue.add(new JsonObjectRequest(
                URL, null, listener, errorListener
        ));
    }

    @Override
    public ImageLoader getImageLoader() {
        return new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> lruCache = new LruCache<>(10);

            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url, bitmap);
            }
        });
    }

    @Override
    public String getImageUrl(String tag) {
        return "http://openweathermap.org/img/w/" + tag + ".png";
    }
}
