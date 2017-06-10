package com.exercises.sart1991.evaluacionfinal8p.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sart1 on 6/9/2017.
 */

public class AppPreferences implements PreferencesHelper {

    private SharedPreferences preferences;
    private final String token_key = "token";

    public AppPreferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public String getToken() {
        return preferences.getString(token_key, null);
    }

    @Override
    public void setToken(String token) {
        preferences.edit().putString(this.token_key, token).apply();
    }
}
