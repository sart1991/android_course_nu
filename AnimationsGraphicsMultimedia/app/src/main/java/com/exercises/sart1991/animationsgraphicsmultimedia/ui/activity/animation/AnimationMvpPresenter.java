package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.animation;

import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/13/2017.
 */

public interface AnimationMvpPresenter<V extends AnimationMvpView> extends MvpPresenter<V> {

    void clickButtonAnimate(int id);

}
