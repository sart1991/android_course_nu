package com.exercises.sart1991.evaluacionfinal11.repository.model;

/**
 * Created by sart1 on 9/28/2017.
 */

public class LittleContact {

    private String contactId;

    private String name;

    private String number;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "LittleContact{" +
                "contactId='" + contactId + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
