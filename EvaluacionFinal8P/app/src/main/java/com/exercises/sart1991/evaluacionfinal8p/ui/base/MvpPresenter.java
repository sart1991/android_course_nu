package com.exercises.sart1991.evaluacionfinal8p.ui.base;

import com.exercises.sart1991.evaluacionfinal8p.data.DataManager;

/**
 * Created by sart1 on 6/7/2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);
    V getMvpView();
    DataManager getDataManager();
    void onDetach();

}
