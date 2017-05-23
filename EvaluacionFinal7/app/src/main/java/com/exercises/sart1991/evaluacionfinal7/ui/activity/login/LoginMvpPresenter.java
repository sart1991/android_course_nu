package com.exercises.sart1991.evaluacionfinal7.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/22/2017.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onLoginClick(String userName, String password);
    void onNewUserClick();
    void onCancelDialogNewUser();
    void onConfPasswordChanged(String password, String confPassword);
    void onCreateNewUser(String userName, String password, String confPassword);
}
