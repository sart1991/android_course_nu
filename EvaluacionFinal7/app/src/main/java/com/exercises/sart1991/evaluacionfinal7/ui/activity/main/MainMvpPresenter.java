package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/19/2017.
 */

interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void validateSession();
}
