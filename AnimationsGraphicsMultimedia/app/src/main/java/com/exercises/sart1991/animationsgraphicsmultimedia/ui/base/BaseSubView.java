package com.exercises.sart1991.animationsgraphicsmultimedia.ui.base;

import android.content.Context;
import android.view.View;

/**
 * Created by sart1 on 6/13/2017.
 */

abstract public class BaseSubView<C extends MvpSubview.Callback> implements MvpSubview<C> {

    private MvpView mMvpView;

    @Override
    public void onAttach(C callback) {
        if (callback instanceof MvpView) {
            mMvpView = (MvpView) callback;
        } else {
            throw new ClassCastException(
                    callback.getClass().getSimpleName() +
                    " should be an instance of " +
                    MvpView.class.getSimpleName()
            );
        }
    }

    @Override
    public C getCallback() {
        return (C) mMvpView;
    }

    @Override
    public void onNotify(String message, View view) {
        mMvpView.onNotify(message, view);
    }

    @Override
    public void onNotify(int resId, View view) {
        mMvpView.onNotify(resId, view);
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
