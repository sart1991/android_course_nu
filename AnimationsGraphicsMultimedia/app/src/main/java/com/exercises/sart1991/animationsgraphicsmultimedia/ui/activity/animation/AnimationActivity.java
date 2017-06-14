package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.exercises.sart1991.animationsgraphicsmultimedia.R;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.BaseActivity;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.MvpView;

public class AnimationActivity extends BaseActivity implements AnimationMvpView {

    private static final String TAG = AnimationActivity.class.getSimpleName();
    private static final AnimationMvpPresenter<AnimationMvpView> PRESENTER = new AnimationPresenter<>();

    private ImageView imgBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        PRESENTER.onAttach(this);
        setUp();
    }

    @Override
    public void setUp() {
        imgBall = (ImageView) findViewById(R.id.imageView_animation_icon);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }

    public void onClickButtonAnimate(View view) {
        PRESENTER.clickButtonAnimate(view.getId());
    }

    @Override
    public void animateFirst() {
        Log.i(TAG, "animateFirst: ");
        AnimatorSet animatorSet =
                (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animation_ball);
        animatorSet.setTarget(imgBall);
        animatorSet.start();
    }

    @Override
    public void animateSecond() {
        Log.i(TAG, "animateSecond: ");
        if (Build.VERSION.SDK_INT >= 23) {
            AnimatorSet animatorSet =
                    (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animation_advance);
            animatorSet.setTarget(imgBall);
            animatorSet.start();
        }
    }
}
