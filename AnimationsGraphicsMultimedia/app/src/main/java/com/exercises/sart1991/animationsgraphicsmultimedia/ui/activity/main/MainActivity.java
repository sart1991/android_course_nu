package com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.exercises.sart1991.animationsgraphicsmultimedia.R;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.activity.animation.AnimationActivity;
import com.exercises.sart1991.animationsgraphicsmultimedia.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PRESENTER.onAttach(this);
        setUp();
    }

    @Override
    public void setUp() {

    }

    public void onClickButtonAnimation(View view) {
        PRESENTER.clickButtonAnimation();
    }

    @Override
    public void gotoAnimation() {
        startActivity(new Intent(this, AnimationActivity.class));
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
