package com.exercises.sart1991.animationsgraphicsmultimedia.ui.base;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by sart1 on 6/13/2017.
 */

abstract public class BaseActivity extends AppCompatActivity implements MvpView {

    @Override
    public void onNotify(int resId, View view) {
        onNotify(getString(resId), view);
    }

    @Override
    public void onNotify(String message, View view) {
        showSnackbar(message, view);
    }

    private void showSnackbar(String message, View view) {
        Snackbar.make(snackView(view), message, Snackbar.LENGTH_LONG).show();
    }

    private View snackView(View view) {
        return view != null ? view : findViewById(android.R.id.content);
    }

    public abstract void setUp();
}
