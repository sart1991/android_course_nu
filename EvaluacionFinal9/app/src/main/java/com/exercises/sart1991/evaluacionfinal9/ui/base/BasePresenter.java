package com.exercises.sart1991.evaluacionfinal9.ui.base;

import com.exercises.sart1991.evaluacionfinal9.data.AppDataManager;
import com.exercises.sart1991.evaluacionfinal9.data.DataManager;

/**
 * Created by sart1 on 6/27/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;
    private DataManager mDataManager;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
        mDataManager = new AppDataManager(mMvpView.getViewContext());
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
        mDataManager = null;
    }
}
