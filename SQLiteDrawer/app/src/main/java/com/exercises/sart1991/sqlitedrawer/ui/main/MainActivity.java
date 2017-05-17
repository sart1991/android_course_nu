package com.exercises.sart1991.sqlitedrawer.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.exercises.sart1991.sqlitedrawer.R;
import com.exercises.sart1991.sqlitedrawer.ui.base.BaseActivity;
import com.exercises.sart1991.sqlitedrawer.ui.insert.InsertFragment;

public class MainActivity extends BaseActivity implements MainMvpView{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        PRESENTER.onAttach(this);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            boolean selected = false;

            switch (id) {
                case R.id.item_drawer_insertOption:
                    PRESENTER.onDrawerOptionInsertClick();
                    selected = true;
                    break;
            }
            drawer.closeDrawer(GravityCompat.START);
            return selected;
        }


    };

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void showInsertFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout_main_fragmentContainer, InsertFragment.newInstance())
                .commit();
    }

    @Override
    public Context getViewContext() {
        return getBaseContext();
    }
}
