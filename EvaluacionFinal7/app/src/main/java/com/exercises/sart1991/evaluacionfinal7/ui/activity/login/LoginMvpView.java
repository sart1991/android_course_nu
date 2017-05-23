package com.exercises.sart1991.evaluacionfinal7.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpView;

/**
 * Created by sart1 on 5/22/2017.
 */

public interface LoginMvpView extends MvpView {

    void onUserNameFieldError(int resId);
    void onPasswordFieldError(int resId);
    void onConfPasswordFieldError(int resId);
    void showDialogForNewUser();
    void makeNewDialogForNewUser();
    void gotoMainActivity();

}
