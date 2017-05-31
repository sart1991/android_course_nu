package com.exercises.sart1991.backgroundtasks.data.api.person.model;

/**
 * Created by sart1 on 5/31/2017.
 */

public class Person {

    private int id;
    private String name;
    private String lastName;

    public Person(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
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
}
