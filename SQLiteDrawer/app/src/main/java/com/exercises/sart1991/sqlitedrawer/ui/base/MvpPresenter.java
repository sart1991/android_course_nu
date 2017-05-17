package com.exercises.sart1991.sqlitedrawer.ui.base;

/**
 * Created by sart1 on 5/16/2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
}
