package com.exercises.sart1991.animationsgraphicsmultimedia.ui.base;

/**
 * Created by sart1 on 6/13/2017.
 */

public interface MvpSubview<C extends MvpSubview.Callback> extends MvpView {

    void onAttach(C callback);

    C getCallback();

    void onDetach();

    interface Callback {

    }

}
