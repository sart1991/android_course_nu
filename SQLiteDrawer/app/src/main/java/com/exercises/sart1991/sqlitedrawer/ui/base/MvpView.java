package com.exercises.sart1991.sqlitedrawer.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by sart1 on 5/16/2017.
 */

public interface MvpView {
    void onError(@StringRes int resId);
    void onError(String message);
    void hideKeyboard();
    Context getViewContext();
}
