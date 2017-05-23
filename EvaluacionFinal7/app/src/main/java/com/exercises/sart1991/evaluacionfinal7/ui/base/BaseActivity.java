package com.exercises.sart1991.evaluacionfinal7.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.exercises.sart1991.evaluacionfinal7.R;

/**
 * Created by sart1 on 5/18/2017.
 */

abstract public class BaseActivity extends AppCompatActivity implements MvpView {

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermissions(String permission) {
        boolean buildVersion = Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
        boolean hasPermission = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        return buildVersion || hasPermission;
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        showSnackBar(message, R.color.primary_dark);
    }

    @Override
    public void onNotify(@StringRes int resId) {
        onNotify(getString(resId));
    }

    @Override
    public void onNotify(String message) {
        showSnackBar(message, R.color.cardview_dark_background);
    }

    @Override
    public void onSuccess(@StringRes int resId) {
        onSuccess(getString(resId));
    }

    @Override
    public void onSuccess(String message) {
        showSnackBar(message, R.color.accent);
    }

    private void showSnackBar(String message, int resColor) {
        Snackbar snackbar = Snackbar.make(
                findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG
        );
        int color = ResourcesCompat.getColor(getResources(), resColor, null);
        snackbar.getView().setBackgroundColor(color);
        snackbar.show();
    }

    @Override
    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    abstract protected void initializeComponents();
}
