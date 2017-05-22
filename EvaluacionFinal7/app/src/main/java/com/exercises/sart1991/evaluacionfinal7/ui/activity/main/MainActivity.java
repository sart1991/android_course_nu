package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.ui.activity.login.LoginActivity;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PRESENTER.onAttach(this);
        PRESENTER.validateSession();
        initializeComponents();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void initializeComponents() {

    }

    public void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}
