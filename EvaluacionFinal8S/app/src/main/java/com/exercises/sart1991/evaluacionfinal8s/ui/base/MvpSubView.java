package com.exercises.sart1991.evaluacionfinal8s.ui.base;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface MvpSubView<C extends MvpSubView.Callback> extends MvpView {

    void onAttach(C callback);
    void onDetach();

    C getCallback();

    interface Callback {

    }
}
