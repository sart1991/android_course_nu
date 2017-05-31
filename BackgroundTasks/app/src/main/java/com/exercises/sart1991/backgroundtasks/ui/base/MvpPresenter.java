package com.exercises.sart1991.backgroundtasks.ui.base;

/**
 * Created by sart1 on 5/30/2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
    void onDetach();
}
