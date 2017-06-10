package com.exercises.sart1991.evaluacionfinal8p.utils;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Course;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static Course fromJsonCourse(JSONObject json) {
        return gson.fromJson(json.toString(), Course.class);
    }

    public static Professor fromJsonProfessor(JSONObject json) {
        return gson.fromJson(json.toString(), Professor.class);
    }

    public static Student fromJsonStudent(JSONObject json) {
        return gson.fromJson(json.toString(), Student.class);
    }

    public static Task fromJsonTask(JSONObject json) {
        return gson.fromJson(json.toString(), Task.class);
    }

}
