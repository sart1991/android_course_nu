package com.exercises.sart1991.evaluacionfinal7.data.db.model;

import com.exercises.sart1991.evaluacionfinal7.data.db.DbInfo;

/**
 * Created by sart1 on 5/22/2017.
 */

public class User {

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
