package com.exercises.sart1991.evaluacionfinal7.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by sart1 on 5/23/2017.
 */

abstract public class BaseSubView implements SubMvpView{

    private MvpView mvpView;
    private MvpPresenter mvpPresenter;

    @Override
    public void onAttach(MvpView mvpView, MvpPresenter mvpPresenter) {
        this.mvpView = mvpView;
        this.mvpPresenter = mvpPresenter;
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
    public void hideKeyBoard() {
        mvpView.hideKeyBoard();
    }

    @Override
    public Context getViewContext() {
        return mvpView.getViewContext();
    }

    @Override
    public void onDetach() {
        mvpView = null;
        mvpPresenter = null;
    }
}
