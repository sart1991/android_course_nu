package com.exercises.sart1991.evaluacionfinal8p.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by sart1 on 6/7/2017.
 */

abstract public class BaseSubView<C extends MvpSubView.Callback> implements MvpSubView<C> {

    private MvpView mMvpView;

    @Override
    public void onAttach(C callback) {
        if (callback instanceof MvpView) {
            mMvpView = (MvpView) callback;
        } else {
            throw new ClassCastException(callback.getClass().getSimpleName() +
                                            " should implement " +
                                            MvpView.class.getSimpleName()
            );
        }
    }

    @Override
    public void onError(@StringRes int resId, @Nullable View view) {
        mMvpView.onError(resId, view);
    }

    @Override
    public void onError(String message, @Nullable View view) {
        mMvpView.onError(message, view);
    }

    @Override
    public void onNotify(@StringRes int resId, @Nullable View view) {
        mMvpView.onNotify(resId, view);
    }

    @Override
    public void onNotify(String message, @Nullable View view) {
        mMvpView.onNotify(message, view);
    }

    @Override
    public void onSuccess(@StringRes int resId, @Nullable View view) {
        mMvpView.onSuccess(resId, view);
    }

    @Override
    public void onSuccess(String message, @Nullable View view) {
        mMvpView.onSuccess(message, view);
    }

    @Override
    public Context getViewContext() {
        return mMvpView.getViewContext();
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }
}
