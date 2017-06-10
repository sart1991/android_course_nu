package com.exercises.sart1991.evaluacionfinal8p.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpView;

/**
 * Created by sart1 on 6/9/2017.
 */

public interface LoginMvpView extends MvpView {

    void gotoMain();

    void errorEmail();

    void errorPassword();
}
