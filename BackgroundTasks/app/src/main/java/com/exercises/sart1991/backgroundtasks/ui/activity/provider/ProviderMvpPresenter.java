package com.exercises.sart1991.backgroundtasks.ui.activity.provider;

import android.app.LoaderManager;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/3/2017.
 */

public interface ProviderMvpPresenter<V extends ProviderMvpView> extends MvpPresenter<V> {

    void generateProvider();
    void insertData();
    void getProviderData(LoaderManager loaderManager);
}
