package com.exercises.sart1991.backgroundtasks.data.api.user.model;

/**
 * Created by sart1 on 5/31/2017.
 */

public class User {
    private int id;
    private String username;
    private String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"username\":\"" + username + "\"" +
                ",\"email\":\"" + email + "\"" +
                "}";
    }
}
