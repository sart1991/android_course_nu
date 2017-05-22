package com.exercises.sart1991.evaluacionfinal7.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by sart1 on 5/19/2017.
 */

public interface MvpView {
    void onError(@StringRes int resId);
    void onError(String message);
    void onNotify(@StringRes int resId);
    void onNotify(String message);
    void hideKeyBoard();
    Context getViewContext();
}
