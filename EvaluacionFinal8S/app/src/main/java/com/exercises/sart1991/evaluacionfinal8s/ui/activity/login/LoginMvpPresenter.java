package com.exercises.sart1991.evaluacionfinal8s.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal8s.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void clickButtonLogin(String email, String password);

    void validateEmail(String email);

}
