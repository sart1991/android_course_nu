package com.exercises.sart1991.backgroundtasks.ui.activity.weather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class WeatherActivity extends BaseActivity implements WeatherMvpView {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final WeatherMvpPresenter<WeatherMvpView> PRESENTER = new WeatherPresenter<>();

    private Toolbar toolbar;
    private NetworkImageView imageWeather;
    private TextView txtWeatherMain, txtWeatherDescription, txtTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initializeComponents();
        PRESENTER.onAttach(this);
        PRESENTER.loadWeatherData();
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageWeather = (NetworkImageView) findViewById(R.id.networkImageView_weather_icon);
        txtWeatherMain = (TextView) findViewById(R.id.textView_weather_main);
        txtWeatherDescription = (TextView) findViewById(R.id.textView_weather_description);
        txtTemperature = (TextView) findViewById(R.id.textView_weather_temp);
    }

    @Override
    public void loadImageWeather(String url, ImageLoader loader) {
        imageWeather.setImageUrl(url, loader);
    }

    @Override
    public void setWeatherMain(String info) {
        txtWeatherMain.setText(info);
    }

    @Override
    public void setWeatherDescription(String info) {
        txtWeatherDescription.setText(info);
    }

    @Override
    public void setTemperature(String info) {
        txtTemperature.setText(info);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}
