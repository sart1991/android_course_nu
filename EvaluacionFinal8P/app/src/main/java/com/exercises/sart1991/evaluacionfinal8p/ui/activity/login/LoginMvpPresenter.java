package com.exercises.sart1991.evaluacionfinal8p.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/9/2017.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void clickButtonLogin(String email, String password);

    void validateEmail(String email);

}
