package com.exercises.sart1991.evaluacionfinal9.ui.activitys.main;

import com.exercises.sart1991.evaluacionfinal9.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/27/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void welcome();

    void clickDrawerOption(int itemId);

}
