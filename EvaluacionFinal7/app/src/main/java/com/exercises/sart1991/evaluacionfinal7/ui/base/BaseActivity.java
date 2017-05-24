package com.exercises.sart1991.evaluacionfinal7.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.exercises.sart1991.evaluacionfinal7.R;

/**
 * Created by sart1 on 5/18/2017.
 */

abstract public class BaseActivity extends AppCompatActivity implements MvpView {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
    public void onError(@StringRes int resId, @Nullable View view) {
        onError(getString(resId), view);
    }

    @Override
    public void onError(String message, @Nullable View view) {
        showSnackBar(message, R.color.primary_dark, view);
    }

    @Override
    public void onNotify(@StringRes int resId, @Nullable View view) {
        onNotify(getString(resId), view);
    }

    @Override
    public void onNotify(String message, @Nullable View view) {
        showSnackBar(message, R.color.cardview_dark_background, view);
    }

    @Override
    public void onSuccess(@StringRes int resId, @Nullable View view) {
        onSuccess(getString(resId), view);
    }

    @Override
    public void onSuccess(String message, @Nullable View view) {
        showSnackBar(message, R.color.accent, view);
    }

    private void showSnackBar(String message, int resColor, @Nullable View view) {
        Snackbar snackbar = Snackbar.make(
                getSnackBarView(view),
                message, Snackbar.LENGTH_LONG
        );
        int color = ResourcesCompat.getColor(getResources(), resColor, null);
        snackbar.getView().setBackgroundColor(color);
        snackbar.show();
    }

    private View getSnackBarView(@Nullable View view) {
        if (view == null) {
            return findViewById(android.R.id.content);
        } else {
            return view;
        }
    }

    @Override
    public void hideKeyBoard() {
        View view = getCurrentFocus();
        Log.i(TAG, "hideKeyBoard: before null");
        if (view != null) {
            Log.i(TAG, "hideKeyBoard: inside null");
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    abstract protected void initializeComponents();
}
