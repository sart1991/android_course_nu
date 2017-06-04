package com.exercises.sart1991.backgroundtasks.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.ui.activity.gson.GsonActivity;
import com.exercises.sart1991.backgroundtasks.ui.activity.http.HttpActivity;
import com.exercises.sart1991.backgroundtasks.ui.activity.json.JsonActivity;
import com.exercises.sart1991.backgroundtasks.ui.activity.provider.ProviderActivity;
import com.exercises.sart1991.backgroundtasks.ui.activity.volley.VolleyActivity;
import com.exercises.sart1991.backgroundtasks.ui.activity.weather.WeatherActivity;
import com.exercises.sart1991.backgroundtasks.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        PRESENTER.onAttach(this);
    }

    @Override
    protected void initializeComponents() {

    }

    public void onClickButtonHttp(View view) {
        PRESENTER.clickButtonHttp();
    }

    @Override
    public void gotoHttp() {
        startActivity(new Intent(this, HttpActivity.class));
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    public void onClickButtonVolley(View view) {
        PRESENTER.clickButtonVolley();
    }

    @Override
    public void gotoVolley() {
        startActivity(new Intent(this, VolleyActivity.class));
    }

    public void onClickButtonJson(View view) {
        PRESENTER.clickButtonJson();
    }

    @Override
    public void gotoJson() {
        startActivity(new Intent(this, JsonActivity.class));
    }

    public void onClickButtonGson(View view) {
        PRESENTER.clickButtonGson();
    }

    @Override
    public void gotoGson() {
        startActivity(new Intent(this, GsonActivity.class));
    }

    public void onClickButtonOpenWeather(View view) {
        PRESENTER.clickButtonOpenWeather();
    }

    @Override
    public void gotoOpenWeather() {
        startActivity(new Intent(this, WeatherActivity.class));
    }

    public void onClickButtonProvider(View view) {
        PRESENTER.clickButtonProvider();
    }

    @Override
    public void gotoProvider() {
        startActivity(new Intent(this, ProviderActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}
