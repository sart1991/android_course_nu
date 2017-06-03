package com.exercises.sart1991.backgroundtasks.ui.activity.weather;

import com.android.volley.toolbox.ImageLoader;
import com.exercises.sart1991.backgroundtasks.ui.base.MvpView;

/**
 * Created by sart1 on 6/2/2017.
 */

public interface WeatherMvpView extends MvpView {

    void loadImageWeather(String url, ImageLoader loader);
    void setWeatherMain(String info);
    void setWeatherDescription(String info);
    void setTemperature(String info);

}
