package com.exercises.sart1991.evaluacionfinal8s.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal8s.ui.base.MvpView;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface LoginMvpView extends MvpView {

    void gotoMain();

    void errorEmail();

    void errorPassword();
}
