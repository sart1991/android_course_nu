package com.exercises.sart1991.backgroundtasks.ui.activity.weather;

import com.exercises.sart1991.backgroundtasks.ui.base.MvpPresenter;

/**
 * Created by sart1 on 6/2/2017.
 */

public interface WeatherMvpPresenter<V extends WeatherMvpView> extends MvpPresenter<V> {
    void loadWeatherData();
}
