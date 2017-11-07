package com.exercise.nextu.evaluacionfinal12.data.repositories.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.exercise.nextu.evaluacionfinal12.App;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class PreferencesRepository {

    private static PreferencesRepository instance;
    private static SharedPreferences preferences;

    private PreferencesRepository() {}

    public static PreferencesRepository get() {
        if (instance == null) instance = getSync();
        return instance;
    }

    private static synchronized PreferencesRepository getSync() {
        if (instance == null) instance = new PreferencesRepository();
        preferences = PreferenceManager.getDefaultSharedPreferences(App.get());
        return instance;
    }

    public boolean isFirstRun() {
        return preferences.getBoolean(PreferencesKeys.FIRST_RUN.getKey(), true);
    }

    public void setFirstRun(boolean firstRun) {
        preferences.edit().putBoolean(PreferencesKeys.FIRST_RUN.getKey(), firstRun).apply();
    }

}
