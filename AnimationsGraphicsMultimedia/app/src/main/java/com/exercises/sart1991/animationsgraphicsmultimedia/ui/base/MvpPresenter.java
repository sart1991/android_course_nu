package com.exercises.sart1991.animationsgraphicsmultimedia.ui.base;

import com.exercises.sart1991.animationsgraphicsmultimedia.data.DataManager;

/**
 * Created by sart1 on 6/13/2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    V getMvpView();

    DataManager getDataManager();

    void onDetach();

}
