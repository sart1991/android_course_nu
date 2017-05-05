package com.exercises.sart1991.evaluacionfinal6;

/**
 * Created by sart1 on 5/5/2017.
 */

public enum MyPreferences {
    PREFERENCE_SESSION("SESSION");

    private String keyValue;

    MyPreferences(String keyValue) {
        this.keyValue = keyValue;
    }

    enum SessionKeys {

        USERNAME_KEY("USERNAME"),
        REMEMBER_KEY("REMEMBER");

        private String keyValue;

        SessionKeys(String keyValue) {
            this.keyValue = keyValue;
        }

        public String getKeyValue() {
            return keyValue;
        }
    }

    public String getKeyValue() {
        return keyValue;
    }
}
