package com.exercises.sart1991.sqlitedrawer.ui.base;

import com.exercises.sart1991.sqlitedrawer.data.AppDataManager;
import com.exercises.sart1991.sqlitedrawer.data.DataManager;

/**
 * Created by sart1 on 5/16/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();
    private DataManager mDataManager;
    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
        mDataManager = new AppDataManager(mvpView.getViewContext());
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
