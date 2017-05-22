package com.exercises.sart1991.evaluacionfinal7.data.preferences;

/**
 * Created by sart1 on 5/22/2017.
 */

public interface PreferencesHelper {

    boolean getLoginState();
    void setLoginState(boolean isLogged);

    String getUserName();
    void setUserName(String userName);

}
