package com.exercises.sart1991.animationsgraphicsmultimedia.ui.base;

import com.exercises.sart1991.animationsgraphicsmultimedia.data.AppDataManager;
import com.exercises.sart1991.animationsgraphicsmultimedia.data.DataManager;

/**
 * Created by sart1 on 6/13/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;
    private DataManager mDataManager;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
        mDataManager = new AppDataManager(mvpView.getViewContext());
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }

    @Override
    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
        mDataManager = null;
    }
}
