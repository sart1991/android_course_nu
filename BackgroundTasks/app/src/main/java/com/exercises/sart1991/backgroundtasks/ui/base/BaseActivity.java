package com.exercises.sart1991.backgroundtasks.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.exercises.sart1991.backgroundtasks.R;

/**
 * Created by sart1 on 5/30/2017.
 */

abstract public class BaseActivity extends AppCompatActivity implements MvpView {

    @Override
    public void onError(@StringRes int resId, @Nullable View view) {
        onError(getString(resId), view);
    }

    @Override
    public void onError(String message, @Nullable View view) {
        showSnackBar(message, R.color.error, view);
    }

    @Override
    public void onNotify(@StringRes int resId, @Nullable View view) {
        onNotify(getString(resId), view);
    }

    @Override
    public void onNotify(String message, @Nullable View view) {
        showSnackBar(message, R.color.notify, view);
    }

    @Override
    public void onSuccess(@StringRes int resId, @Nullable View view) {
        onSuccess(getString(resId), view);
    }

    @Override
    public void onSuccess(String message, @Nullable View view) {
        showSnackBar(message, R.color.success, view);
    }

    private void showSnackBar(String message, int resColor, @Nullable View view) {
        Snackbar snackbar = Snackbar.make(
                getSnackBarView(view),
                message, BaseTransientBottomBar.LENGTH_LONG
        );
        snackbar.getView().setBackgroundResource(resColor);
        snackbar.show();
    }

    private View getSnackBarView(View view) {
        if (view == null) {
            return findViewById(android.R.id.content);
        }
        return view;
    }

    protected abstract void initializeComponents();
}
