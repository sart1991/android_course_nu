package com.exercises.sart1991.evaluacionfinal8s.ui.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.exercises.sart1991.evaluacionfinal8s.R;

/**
 * Created by sart1 on 6/11/2017.
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

    private void showSnackBar(String message, @ColorRes int resColor, @Nullable View view) {
        Snackbar snackbar = Snackbar.make(snackView(view), message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundResource(resColor);
        snackbar.show();
    }

    private View snackView(View view) {
        return view == null ? findViewById(android.R.id.content) : view;
    }

    @Override
    public boolean checkInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    abstract protected void setupComponents();
}
