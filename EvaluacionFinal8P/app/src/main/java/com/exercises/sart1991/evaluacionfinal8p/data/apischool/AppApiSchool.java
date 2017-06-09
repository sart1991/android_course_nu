package com.exercises.sart1991.evaluacionfinal8p.data.apischool;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/7/2017.
 */

public class AppApiSchool implements ApiSchoolHelper {

    private final String url = "http://192.168.1.58:8080/api-school";
    private RequestQueue queue;

    @Override
    public void provideApiSchoolContext(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public void getCourses(final ListenRequest<List<Course>> listener) {
        queue.add(new JsonArrayRequest(
                url + "/professor_courses",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Course> courses = new ArrayList<>();
                        listener.onSuccess(courses);
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
    public void getStudents(final ListenRequest<List<Student>> listener) {
        queue.add(new JsonArrayRequest(
                url + "/professor_students",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Student> students = new ArrayList<>();
                        listener.onSuccess(students);
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
    public void getTasks(final ListenRequest<List<Task>> listener) {
        queue.add(new JsonArrayRequest(
                url + "/professor_tasks",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Task> tasks = new ArrayList<>();
                        listener.onSuccess(tasks);
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
