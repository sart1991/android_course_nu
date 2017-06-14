package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.main;

import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.BasePresenter;

/**
 * Created by sart1 on 6/13/2017.
 */

public class MainPresenter<V extends MainMvpView>
        extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Override
    public void clickButtonAnimation() {
        getMvpView().gotoAnimation();
    }
}
