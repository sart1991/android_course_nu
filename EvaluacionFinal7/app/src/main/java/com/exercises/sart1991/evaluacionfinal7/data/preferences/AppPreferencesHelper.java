package com.exercises.sart1991.evaluacionfinal7.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sart1 on 5/22/2017.
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED = "PREF_KEY_USER_LOGGED";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME";

    private SharedPreferences preferences;

    public AppPreferencesHelper(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public boolean getLoginState() {
        return preferences.getBoolean(PREF_KEY_USER_LOGGED, false);
    }

    @Override
    public void setLoginState(boolean isLogged) {
        preferences.edit().putBoolean(PREF_KEY_USER_LOGGED, isLogged).apply();
    }

    @Override
    public String getUserName() {
        return preferences.getString(PREF_KEY_USER_NAME, null);
    }

    @Override
    public void setUserName(String userName) {
        preferences.edit().putString(PREF_KEY_USER_NAME, userName).apply();
    }
}
