package com.super_test.thesupertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SergioAlejandro on 2/09/2016.
 */
public class RequestCall implements ImageLoader.ImageCache{

    private JSONObject jsonInfo;
    private Context context;
    private String url;
    private String userToken;
    private String TAG = "RequestCall.class";
    private JSONObject jsonRawObject;

    //Logging in doRequest
    public RequestQueue doRequest(Context context, String name, String password) {
        this.context = context;
        this.url = RequestedURLS.LOGIN_URL.getUrl() + "username=" + name + "&password=" + password;
        return doRequest(Request.Method.GET);
    }

    public RequestQueue doRequest(Context context) {
        this.context = context;
        this.url = RequestedURLS.PRODUCTS_URL.getUrl();
        return doRequest(Request.Method.GET);
    }

    public RequestQueue doRequest (Context context, Product product) {
        this.context = context;
        this.url = RequestedURLS.PRODUCTS_URL.getUrl() + "/" + product.getObjectId();
        try {
            this.jsonRawObject = new JSONObject(product.toString());
        } catch (JSONException e) {
            Log.i(TAG, e.getMessage());
        }
        return doRequest(Request.Method.PUT);
    }

    private RequestQueue doRequest(int requestMethod) {

        final String appId = context.getResources().getString(R.string.application_id);
        final String appKey = context.getResources().getString(R.string.application_master_key);

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonRqst = new JsonObjectRequest(requestMethod, this.url, jsonRawObject,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // The positive response to the request save the json object in global variable
                    jsonInfo = response;
                    Log.i(TAG, "#(1) " + jsonInfo.toString());
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // In case the request failed
                    Log.i(TAG, "#(2) " + error.getMessage());
                }
            }
        )
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<>();
                    params.put("X-Parse-Application-Id", appId);
                    params.put("X-Parse-Master-Key", appKey);
                    params.put("X-Parse-Session-Token", userToken);

                    return params;
                }
            };

        queue.add(jsonRqst);

        return queue;
    }


    // Returns the json object obtained from the request
    public JSONObject getJson() {
        if(jsonInfo!=null) {
            return this.jsonInfo;
        } else {
            return new JSONObject();
        }
    }


    public ImageLoader getImageFromUrl(Context context) {
        RequestQueue requestImage = Volley.newRequestQueue(context);
        return new ImageLoader(requestImage, this);
    }

    @Override
    public Bitmap getBitmap(String url) {
        final LruCache<String, Bitmap> cache = new LruCache<>(20);
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

    }
}
