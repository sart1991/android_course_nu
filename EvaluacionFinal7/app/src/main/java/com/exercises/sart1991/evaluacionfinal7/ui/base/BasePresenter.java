package com.exercises.sart1991.evaluacionfinal7.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;

import com.exercises.sart1991.evaluacionfinal7.data.AppDataManager;
import com.exercises.sart1991.evaluacionfinal7.data.DataManager;

/**
 * Created by sart1 on 5/19/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private DataManager mDataManager;
    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
        mDataManager = new AppDataManager(mvpView.getViewContext());
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return  mMvpView;
    }

    public boolean isDataManagerAttached() {
        return  isViewAttached();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    public boolean isSessionActive() {
        return  getDataManager().getLoginState();
    }
}
