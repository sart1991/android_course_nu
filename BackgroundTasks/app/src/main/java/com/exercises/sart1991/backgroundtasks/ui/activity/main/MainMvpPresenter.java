package com.exercises.sart1991.backgroundtasks.ui.activity.main;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/30/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void clickButtonHttp();
    void clickButtonVolley();
    void clickButtonJson();
    void clickButtonGson();
    void clickButtonOpenWeather();
    void clickButtonProvider();
}
