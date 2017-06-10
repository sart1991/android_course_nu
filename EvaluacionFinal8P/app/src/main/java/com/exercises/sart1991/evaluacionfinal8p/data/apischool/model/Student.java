package com.exercises.sart1991.evaluacionfinal8p.data.apischool.model;

/**
 * Created by sart1 on 6/7/2017.
 */

public class Student {

    private String name;
    private String email;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*@Override
    public String toString() {
        return "{" +
                "\"id\":" + " " + id + ", " +
                "\"name\":" + " \"" + name + "\", " +
                "\"email\":" + " \"" + email + "\"" +
                "}";
    }*/
}
