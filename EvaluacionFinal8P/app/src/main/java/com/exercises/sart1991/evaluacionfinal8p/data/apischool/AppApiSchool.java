package com.exercises.sart1991.evaluacionfinal8p.data.apischool;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8p.data.preferences.AppPreferences;
import com.exercises.sart1991.evaluacionfinal8p.data.preferences.PreferencesHelper;
import com.exercises.sart1991.evaluacionfinal8p.utils.JsonConverter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sart1 on 6/7/2017.
 */

public class AppApiSchool implements ApiSchoolHelper {

    private static final String TAG = AppApiSchool.class.getSimpleName();

    private final String url = "http://192.168.1.58:8080/api-school";
    private final String headerAuth = "Authorization";
    private RequestQueue queue;
    private PreferencesHelper preferencesHelper;

    public AppApiSchool(Context context) {
        queue = Volley.newRequestQueue(context);
        preferencesHelper = new AppPreferences(context);
    }

    @Override
    public void getTokenProfessor(Professor professor, final ListenRequest<String> listener) {
//                Log.i(TAG, "getTokenProfessor: new JSON " + new JSONObject(professor.toString()));
        Log.i(TAG, "getTokenProfessor: JsonConverter " + JsonConverter.toJson(professor));
        queue.add(new JsonObjectRequest(
                Request.Method.POST, url + "/token_professor",
                JsonConverter.toJson(professor),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                        listener.onError();
                    }
                }
        ));
    }

    @Override
    public void checkProfessorLogin(final ListenRequest<Professor> listener) {
        queue.add(new JsonObjectRequest(
                url + "/professor", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(JsonConverter.fromJsonProfessor(response));
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

    @Override
    public void getCourses(final ListenRequest<List<Course>> listener) {
        queue.add(new JsonArrayRequest(
                url + "/courses",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "onResponse: courses " + response);
                        listener.onSuccess(JsonConverter.fromJsonArrayCourses(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: listenCourses " + error.getMessage());
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

    @Override
    public void getStudents(final ListenRequest<List<Student>> listener) {
        queue.add(new JsonArrayRequest(
                url + "/students",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listener.onSuccess(JsonConverter.fromJsonArrayStudents(response));
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

    @Override
    public void getTasks(final ListenRequest<List<Task>> listener) {
        queue.add(new JsonArrayRequest(
                url + "/tasks",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, "onResponse: response: " + response);
                        listener.onSuccess(JsonConverter.fromJsonArrayTasks(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: getTasks: " + error);
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

    @Override
    public void postTask(Task task, @Nullable final ListenRequest<Task> listener) {
        Log.i(TAG, "postTask: " + task);
        queue.add(new JsonObjectRequest(
                Request.Method.POST, url + "/tasks",
                JsonConverter.toJson(task),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: postTask: " + response);
                        if (listener != null) {
                            listener.onSuccess(JsonConverter.fromJsonTask(response));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: postTask: " + error.getMessage());
                        if (listener != null) {
                            listener.onError();
                        }
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

    @Override
    public void putTask(Task task, @Nullable final ListenRequest<Task> listener) {
        Log.i(TAG, "putTask: " + task);
        queue.add(new JsonObjectRequest(
                Request.Method.PUT, url + "/tasks/" + task.getId(),
                JsonConverter.toJson(task),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: putTask: " + response);
                        if (listener != null) {
                            listener.onSuccess(JsonConverter.fromJsonTask(response));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: putTask: " + error.getMessage());
                        if (listener != null) {
                            listener.onError();
                        }
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

    @Override
    public void deleteTask(Task task, @Nullable ListenRequest<Task> listener) {
        deleteTask(task.getId(), listener);
    }

    @Override
    public void deleteTask(int taskId, @Nullable final ListenRequest<Task> listener) {
        Log.i(TAG, "deleteTask: " + taskId);
        queue.add(new JsonObjectRequest(
                Request.Method.DELETE,
                url + "/tasks/" + taskId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: listenUpdateTasks: " + listener);
                        Log.i(TAG, "onResponse: listenUpdateTasks: " + String.valueOf(listener != null));
                        if (listener != null) {
                            listener.onSuccess(JsonConverter.fromJsonTask(response));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onResponse: listenUpdateTasks: errorDelete: " + error.getMessage());
                        Log.i(TAG, "onResponse: listenUpdateTasks: errorDelete: " + listener);
                        if (listener != null) {
                            listener.onError();
                        }
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

    @Override
    public void testApiSchool(final ListenRequest<String> listener) {
        queue.add(new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError();
                    }
                }
        ));
    }
}
