package com.exercises.sart1991.backgroundtasks.ui.activity.main;

import com.exercises.sart1991.backgroundtasks.ui.base.BasePresenter;

/**
 * Created by sart1 on 5/30/2017.
 */

public class MainPresenter<V extends MainMvpView>
        extends BasePresenter<V> implements MainMvpPresenter<V> {
    @Override
    public void clickButtonHttp() {
        getMvpView().gotoHttp();
    }

    @Override
    public void clickButtonVolley() {
        getMvpView().gotoVolley();
    }

    @Override
    public void clickButtonJson() {
        getMvpView().gotoJson();
    }

    @Override
    public void clickButtonGson() {
        getMvpView().gotoGson();
    }

    @Override
    public void clickButtonOpenWeather() {
        getMvpView().gotoOpenWeather();
    }
}
