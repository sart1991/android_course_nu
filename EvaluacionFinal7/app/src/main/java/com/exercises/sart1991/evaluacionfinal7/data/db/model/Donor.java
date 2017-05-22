package com.exercises.sart1991.evaluacionfinal7.data.db.model;

import com.exercises.sart1991.evaluacionfinal7.data.db.DbHelper;
import com.exercises.sart1991.evaluacionfinal7.data.db.DbInfo;

/**
 * Created by sart1 on 5/22/2017.
 */

public class Donor {

    private int id;
    private String name;
    private String lastName;
    private  int age;
    private String bloodType;
    private String rh;
    private int weight;
    private int height;
    private String forUserName;

    public Donor(int id, String name, String lastName, int age, String bloodType, String rh,
                 int weight, int height, String forUserName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.bloodType = bloodType;
        this.rh = rh;
        this.weight = weight;
        this.height = height;
        this.forUserName = forUserName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getForUserName() {
        return forUserName;
    }

    public void setForUserName(String forUserName) {
        this.forUserName = forUserName;
    }
}
