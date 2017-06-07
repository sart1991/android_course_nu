package com.exercises.sart1991.evaluacionfinal8p.ui.base;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface MvpSubView<C extends MvpSubView.Callback> extends MvpView {

    void onAttach(C callback);
    void onDetach();

    interface Callback {

    }
}
