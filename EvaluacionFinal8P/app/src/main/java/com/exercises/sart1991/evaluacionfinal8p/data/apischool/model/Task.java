package com.exercises.sart1991.evaluacionfinal8p.data.apischool.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sart1 on 6/7/2017.
 */

public class Task {

    private int id;
    private String name;
    private @SerializedName("grade_point") double gradePoint;

    public Task(int id, String description) {
        this.id = id;
        this.name = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }

    /*@Override
    public String toString() {
        return "{" +
                "\"id\":" + " " + id + ", " +
                "\"name\":" + " \"" + name + "\"" +
                "}";
    }*/
}
