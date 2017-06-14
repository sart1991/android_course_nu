package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.main;

import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/13/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void clickButtonAnimation();
}
