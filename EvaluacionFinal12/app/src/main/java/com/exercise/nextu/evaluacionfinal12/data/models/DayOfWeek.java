package com.exercise.nextu.evaluacionfinal12.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

@Entity(tableName = "days_of_week")
public class DayOfWeek {

    @PrimaryKey
    private int id;

    private String name;

    private long balance;

    public DayOfWeek() {
    }

    public DayOfWeek(int id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
