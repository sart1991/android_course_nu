package com.exercises.sart1991.evaluacionfinal7.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by sart1 on 5/19/2017.
 */

public interface MvpView {
    void onError(@StringRes int resId, @Nullable View view);
    void onError(String message, @Nullable View view);
    void onNotify(@StringRes int resId, @Nullable View view);
    void onNotify(String message, @Nullable View view);
    void onSuccess(@StringRes int resId, @Nullable View view);
    void onSuccess(String message, @Nullable View view);
    void hideKeyBoard();
    Context getViewContext();
}
