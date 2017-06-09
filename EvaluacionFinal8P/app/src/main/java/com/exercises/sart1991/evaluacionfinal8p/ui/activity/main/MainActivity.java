package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.ui.activity.login.LoginActivity;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    private final MainMvpPresenter<MainMvpView> presenter = new MainPresenter<>();

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.onAttach(this);
        setupComponents();
    }

    @Override
    protected void setupComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawerOpen, R.string.drawerClose
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            };

    @Override
    public void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
