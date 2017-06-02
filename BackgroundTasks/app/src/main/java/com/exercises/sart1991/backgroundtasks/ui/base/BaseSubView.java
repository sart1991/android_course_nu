package com.exercises.sart1991.backgroundtasks.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by sart1 on 6/2/2017.
 */

public class BaseSubView<C extends MvpSubView.Callback> implements MvpSubView<C> {

    private MvpView mvpView;

    @Override
    public void onAttach(C callback) {
        if (callback instanceof MvpView) {
            mvpView = (MvpView) callback;
        } else {
            throw new ClassCastException("Should implement " + MvpView.class.getSimpleName());
        }
    }

    @Override
    public void onError(@StringRes int resId, @Nullable View view) {
        mvpView.onError(resId, view);
    }

    @Override
    public void onError(String message, @Nullable View view) {
        mvpView.onError(message, view);
    }

    @Override
    public void onNotify(@StringRes int resId, @Nullable View view) {
        mvpView.onNotify(resId, view);
    }

    @Override
    public void onNotify(String message, @Nullable View view) {
        mvpView.onNotify(message, view);
    }

    @Override
    public void onSuccess(@StringRes int resId, @Nullable View view) {
        mvpView.onSuccess(resId, view);
    }

    @Override
    public void onSuccess(String message, @Nullable View view) {
        mvpView.onSuccess(message, view);
    }

    @Override
    public Context getViewContext() {
        return mvpView.getViewContext();
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }
}
