package com.exercises.sart1991.backgroundtasks.ui.activity.unit2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class Unit2Activity extends BaseActivity implements Unit2MvpView {

    private static final Unit2MvpPresenter<Unit2MvpView> PRESENTER = new Unit2Presenter<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit2);
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
}
