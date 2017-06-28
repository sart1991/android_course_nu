package com.exercises.sart1991.evaluacionfinal9.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by sart1 on 6/27/2017.
 */

public interface MvpView {

    void onNotify(@StringRes int resId);

    void onNotify(String message);

    Context getViewContext();

}
