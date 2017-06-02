package com.exercises.sart1991.backgroundtasks.data.api.user;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sart1 on 5/31/2017.
 */

public class AppApiUserConnection implements ApiUserConnection {

    private static final String TAG = AppApiUserConnection.class.getSimpleName();

    private String url;
    private RequestQueue queue;

    public AppApiUserConnection(Context context) {
        url = "http://192.168.1.58:8086/nextuniversity/webapi";
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public String getUsers() {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users"));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return "";
    }

    @Override
    public void postUser(User user) {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users"));
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(user.toString().getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(user.toString().getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public void putUser(User user) {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users/" + user.getId()));
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(user.toString().getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(user.toString().getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public void deleteUser(int userId) {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users/" + userId));
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(String.valueOf(userId).getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(String.valueOf(userId).getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    private HttpURLConnection connectToUrl(URL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getVolleyUsers(Response.Listener<String> listener,
                               Response.ErrorListener errorListener) {
        queue.add(new StringRequest(Request.Method.GET, url + "/users", listener, errorListener));
    }

    @Override
    public void postVolleyUser(JSONObject jsonObject,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener) {

        queue.add(new JsonObjectRequest(
                Request.Method.POST, url + "/users",
                jsonObject, listener, errorListener
        ));
    }

    @Override
    public void putVolleyUser(JSONObject jsonObject,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener) {
        int id = 0;
        try {
            id = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        queue.add(new JsonObjectRequest(
                Request.Method.PUT, url + "/users/" + id,
                jsonObject, listener, errorListener
        ));
    }

    @Override
    public void deleteVolleyUser(int id,
                                 Response.Listener<String> listener,
                                 Response.ErrorListener errorListener) {

        queue.add(new StringRequest(
                Request.Method.DELETE, url + "/users/" + id,
                listener, errorListener
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }


        });
    }
}
