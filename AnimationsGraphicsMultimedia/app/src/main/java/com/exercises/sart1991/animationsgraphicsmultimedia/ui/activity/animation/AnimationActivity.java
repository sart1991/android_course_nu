package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.exercises.sart1991.animationsgraphicsmultimedia.R;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.BaseActivity;

public class AnimationActivity extends BaseActivity implements AnimationMvpView {

    private static final String TAG = AnimationActivity.class.getSimpleName();
    private static final AnimationMvpPresenter<AnimationMvpView> PRESENTER = new AnimationPresenter<>();

    private ImageView imgBall;
    AnimatorSet mAnimatorSet;

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
        mAnimatorSet =
                (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animation_ball);
        mAnimatorSet.setTarget(imgBall);
        mAnimatorSet.addListener(animatorListener);
    }

    public void onClickButtonAnimate(View view) {
        PRESENTER.clickButtonAnimate(view.getId());
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            imgBall.setElevation(16f);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            PRESENTER.animationCanceled();
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    @Override
    public void animateFirst() {
        Log.i(TAG, "animateFirst: ");
        mAnimatorSet.start();
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

    @Override
    public void animationThird() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.ball);
        imgBall.startAnimation(animation);
    }

    @Override
    public void animateCancel() {
        mAnimatorSet.cancel();
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
}
