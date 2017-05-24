package com.exercises.sart1991.evaluacionfinal7.ui.base;

/**
 * Created by sart1 on 5/23/2017.
 */

public interface SubMvpView extends MvpView {
    void onAttach(MvpView mvpView, MvpPresenter mvpPresenter);
    void onDetach();
}
