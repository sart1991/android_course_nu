package com.exercises.sart1991.evaluacionfinal8p.utils;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sart1 on 6/9/2017.
 */

public class JsonConverter {

    private static Gson gson = new Gson();

    private JsonConverter() {}

    public static <O> JSONObject toJson(O object) {
        try {
            return new JSONObject(gson.toJson(object));
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static Professor fromJsonProfessor(JSONObject json) {
        return gson.fromJson(json.toString(), Professor.class);
    }

    public static Course fromJsonCourse(JSONObject json) {
        return gson.fromJson(json.toString(), Course.class);
    }

    public static List<Course> fromJsonArrayCourses(JSONArray jsonArray) {
        return Arrays.asList(gson.fromJson(jsonArray.toString(), Course[].class));
    }

    public static Student fromJsonStudent(JSONObject json) {
        return gson.fromJson(json.toString(), Student.class);
    }

    public static List<Student> fromJsonArrayStudents(JSONArray jsonArray) {
        return Arrays.asList(gson.fromJson(jsonArray.toString(), Student[].class));
    }

    public static Task fromJsonTask(JSONObject json) {
        Task task = null;
        try {
            task = new Task(
                    json.getInt("id"),
                    json.getString("name"),
                    json.getJSONObject("Student").getString("name"),
                    json.getJSONObject("Course").getString("name"),
                    json.getDouble("grade_point")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return task;
    }

    public static List<Task> fromJsonArrayTasks(JSONArray jsonArray) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                tasks.add(fromJsonTask(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }


}
