package com.exercises.sart1991.startsessionsharedpreferences;

/**
 * Created by sart1 on 4/26/2017.
 */

public enum PreferenceConstants {

    PREFERENCE_LOGIN("user_logged_in");

    private final String content;

    public enum Login {
        KEY_USERNAME("username"),
        KEY_SESSION("session");

        private final String content;

        Login(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

    PreferenceConstants(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
