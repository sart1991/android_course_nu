package com.exercises.sart1991.evaluacionfinal8s.ui.activity.main;

import android.app.LoaderManager;

import com.exercises.sart1991.evaluacionfinal8s.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/11/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void welcome();

    void clickOptionsMenu(int itemId);

    void getProviderData(LoaderManager loaderManager);

}
