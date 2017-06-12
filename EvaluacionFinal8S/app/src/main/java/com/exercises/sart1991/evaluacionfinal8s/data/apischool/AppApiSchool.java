package com.exercises.sart1991.evaluacionfinal8s.data.apischool;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8s.data.preferences.AppPreferences;
import com.exercises.sart1991.evaluacionfinal8s.data.preferences.PreferencesHelper;
import com.exercises.sart1991.evaluacionfinal8s.utils.JsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sart1 on 6/11/2017.
 */

public class AppApiSchool implements ApiSchoolHelper {

    private static final String TAG = AppApiSchool.class.getSimpleName();

    private final String url = "http://sart1991.cleverapps.io/api-school";
    private final String headerAuth = "Authorization";
    private RequestQueue queue;
    private PreferencesHelper preferencesHelper;

    public AppApiSchool(Context context) {
        queue = Volley.newRequestQueue(context);
        preferencesHelper = new AppPreferences(context);
    }

    @Override
    public void getTokenStudent(Student student, final ListenRequest<String> listener) {
        Log.i(TAG, "getTokenStudent: " + student);
        Log.i(TAG, "getTokenStudent: " + JsonConverter.toJson(student));
        queue.add(new JsonObjectRequest(
                Request.Method.POST, url + "/token_student",
                JsonConverter.toJson(student),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response);
                        try {
                            listener.onSuccess(response.getString("token"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: " + error.getMessage());
                        listener.onError();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        });
    }

    @Override
    public void checkStudentLogin(final ListenRequest<Student> listener) {
        queue.add(new JsonObjectRequest(
                url + "/student", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(JsonConverter.fromJsonStudent(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put(headerAuth, preferencesHelper.getToken());
                return headers;
            }
        });
    }
}
