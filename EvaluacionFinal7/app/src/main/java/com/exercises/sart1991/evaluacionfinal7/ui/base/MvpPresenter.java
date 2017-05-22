package com.exercises.sart1991.evaluacionfinal7.ui.base;

/**
 * Created by sart1 on 5/19/2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
}
