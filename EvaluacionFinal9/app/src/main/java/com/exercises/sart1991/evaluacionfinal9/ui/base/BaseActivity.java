package com.exercises.sart1991.evaluacionfinal9.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sart1 on 6/27/2017.
 */

abstract public class BaseActivity extends AppCompatActivity implements MvpView {

    @Override
    public void onNotify(@StringRes int resId) {
        onNotify(getString(resId));
    }

    @Override
    public void onNotify(String message) {
        showSnackbar(message);
    }

    private void showSnackbar(String message) {
        Snackbar.make(
                findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    abstract protected void setUp();
}
