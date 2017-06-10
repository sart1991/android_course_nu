package com.exercises.sart1991.evaluacionfinal8p.data.apischool.model;

/**
 * Created by sart1 on 6/7/2017.
 */

public class Course {

    private int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
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

    /*@Override
    public String toString() {
        return "{" +
                "\"id\":" + " " + id + "," +
                "\"name\":" + " \"" + name + '\"' +
                "}";
    }*/
}
