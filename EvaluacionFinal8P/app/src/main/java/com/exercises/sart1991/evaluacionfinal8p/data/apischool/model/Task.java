package com.exercises.sart1991.evaluacionfinal8p.data.apischool.model;

/**
 * Created by sart1 on 6/7/2017.
 */

public class Task {

    private int id;
    private String description;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
