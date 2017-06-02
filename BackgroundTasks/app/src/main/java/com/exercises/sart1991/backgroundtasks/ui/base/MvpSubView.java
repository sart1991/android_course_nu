package com.exercises.sart1991.backgroundtasks.ui.base;

/**
 * Created by sart1 on 6/2/2017.
 */

public interface MvpSubView<C extends MvpSubView.Callback> extends MvpView {

    void onAttach(C callback);
    void onDetach();

    interface Callback {

    }
}
