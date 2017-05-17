package com.exercises.sart1991.sqlitedrawer.ui.main;

import com.exercises.sart1991.sqlitedrawer.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/16/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onDrawerOptionInsertClick();
}
