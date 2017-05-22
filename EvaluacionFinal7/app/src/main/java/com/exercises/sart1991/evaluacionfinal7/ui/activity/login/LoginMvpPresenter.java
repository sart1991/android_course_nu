package com.exercises.sart1991.evaluacionfinal7.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/22/2017.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void validateUserName(String userName);
    void validatePassword(String password);
}
