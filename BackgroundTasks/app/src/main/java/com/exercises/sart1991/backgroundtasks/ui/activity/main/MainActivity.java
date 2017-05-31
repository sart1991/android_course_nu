package com.exercises.sart1991.backgroundtasks.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.activity.unit2.Unit2Activity;
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
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void initializeComponents() {

    }

    public void onClickButtonUnit2(View view) {
        PRESENTER.clickButtonUnit2();
    }

    @Override
    public void gotoUnit2() {
        startActivity(new Intent(this, Unit2Activity.class));
    }
}
