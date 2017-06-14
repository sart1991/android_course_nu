package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.animation;

import com.exercises.sart1991.animationsgraphicsmultimedia.R;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.BasePresenter;

/**
 * Created by sart1 on 6/13/2017.
 */

public class AnimationPresenter<V extends AnimationMvpView>
        extends BasePresenter<V> implements AnimationMvpPresenter<V> {

    @Override
    public void clickButtonAnimate(int id) {
        switch (id) {
            case R.id.button_animation_animate1:
                getMvpView().animateFirst();
                break;
            case R.id.button_animation_animate2:
                getMvpView().animateSecond();
                break;
        }
    }
}
