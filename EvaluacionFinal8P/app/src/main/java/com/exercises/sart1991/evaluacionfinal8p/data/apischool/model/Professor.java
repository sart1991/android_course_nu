package com.exercises.sart1991.evaluacionfinal8p.data.apischool.model;

/**
 * Created by sart1 on 6/7/2017.
 */

public class Professor {

    private int id;
    private String name;
    private String email;
    private String password;

    public Professor(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Professor(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
