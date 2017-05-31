package com.exercises.sart1991.backgroundtasks.ui.base;

import com.exercises.sart1991.backgroundtasks.data.AppDataManager;
import com.exercises.sart1991.backgroundtasks.data.DataManager;

/**
 * Created by sart1 on 5/30/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mvpView;
    private DataManager dataManager;

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
        dataManager = new AppDataManager(mvpView.getViewContext());
    }

    public V getMvpView() {
        return mvpView;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onDetach() {
        mvpView = null;
        dataManager = null;
    }
}
