package com.exercises.sart1991.almacenamientodeobjetos;

import java.io.Serializable;

/**
 * Created by sart1 on 5/3/2017.
 */

public class Person implements Serializable {

    private int id;
    private String name;
    private String lastname;

    public Person(int id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", lastname = '" + lastname + '\'' +
                '}';
    }
}
