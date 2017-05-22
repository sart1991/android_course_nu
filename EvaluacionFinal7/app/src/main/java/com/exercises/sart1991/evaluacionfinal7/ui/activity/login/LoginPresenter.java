package com.exercises.sart1991.evaluacionfinal7.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal7.ui.base.BasePresenter;

/**
 * Created by sart1 on 5/22/2017.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {


    @Override
    public void validateUserName(String userName) {
        getMvpView().onError("Error");
    }

    @Override
    public void validatePassword(String password) {
        getMvpView().onNotify("Message");
    }
}
