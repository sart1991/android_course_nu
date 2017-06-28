package com.exercises.sart1991.evaluacionfinal9.ui.base;

/**
 * Created by sart1 on 6/27/2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

}
