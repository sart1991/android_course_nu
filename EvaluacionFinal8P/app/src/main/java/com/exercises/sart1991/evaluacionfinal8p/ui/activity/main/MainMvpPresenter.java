package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import com.exercises.sart1991.evaluacionfinal8p.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void welcome();

    boolean clickSignOut(int itemId);

}
