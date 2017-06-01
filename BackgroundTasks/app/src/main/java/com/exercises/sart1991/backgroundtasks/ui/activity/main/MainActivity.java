package com.exercises.sart1991.backgroundtasks.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.activity.http.HttpActivity;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        PRESENTER.onAttach(this);
    }

    @Override
    protected void initializeComponents() {

    }

    public void onClickButtonHttp(View view) {
        PRESENTER.clickButtonHttp();
    }

    @Override
    public void gotoUnit2() {
        startActivity(new Intent(this, HttpActivity.class));
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
