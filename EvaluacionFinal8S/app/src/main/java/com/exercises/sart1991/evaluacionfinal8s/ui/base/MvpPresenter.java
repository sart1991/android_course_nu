package com.exercises.sart1991.evaluacionfinal8s.ui.base;

import com.exercises.sart1991.evaluacionfinal8s.data.DataManager;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);
    V getMvpView();
    DataManager getDataManager();
    void onDetach();

}
