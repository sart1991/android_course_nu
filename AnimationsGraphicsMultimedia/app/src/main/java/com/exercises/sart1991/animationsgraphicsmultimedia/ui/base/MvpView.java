package com.exercises.sart1991.animationsgraphicsmultimedia.ui.base;

import android.content.Context;
import android.view.View;

/**
 * Created by sart1 on 6/13/2017.
 */

public interface MvpView {

    void onNotify(String message, View view);

    void onNotify(int resId, View view);

    Context getViewContext();

}
