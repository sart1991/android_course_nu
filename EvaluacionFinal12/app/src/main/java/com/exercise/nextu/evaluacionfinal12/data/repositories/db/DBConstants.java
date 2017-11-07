package com.exercise.nextu.evaluacionfinal12.data.repositories.db;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public enum DBConstants {
    TABLE_NAME_DAYS_OF_WEEK("days_of_week");

    private String key;

    DBConstants(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
