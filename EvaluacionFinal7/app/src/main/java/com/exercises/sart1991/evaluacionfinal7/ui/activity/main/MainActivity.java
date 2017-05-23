package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.ui.activity.login.LoginActivity;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PRESENTER.onAttach(this);
        PRESENTER.validateSession();
        initializeComponents();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PRESENTER.onOptionItemClick(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intentHome = new Intent(Intent.ACTION_MAIN);
        intentHome.addCategory(Intent.CATEGORY_HOME);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentHome);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}
