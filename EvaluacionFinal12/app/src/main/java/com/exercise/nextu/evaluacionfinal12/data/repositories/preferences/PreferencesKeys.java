package com.exercise.nextu.evaluacionfinal12.data.repositories.preferences;

import java.security.Key;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public enum PreferencesKeys {

    FIRST_RUN("firstRun");

    private String key;

    PreferencesKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
