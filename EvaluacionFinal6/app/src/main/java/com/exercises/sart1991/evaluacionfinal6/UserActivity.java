package com.exercises.sart1991.evaluacionfinal6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SharedPreferences sessionSharedPreferences;
    private String userName;
    private boolean remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeComponents();
    }

    private NavigationView.OnNavigationItemSelectedListener onNavItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            };


    private View.OnClickListener onFloatClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    public void onBackPressed() {
        if (!remember) super.onBackPressed();
        else startActivity(new Intent(Intent.ACTION_MAIN));
    }

    private void showSnackbar(String message) {
        Snackbar.make(drawerLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void initializeComponents() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navView = (NavigationView) findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(onNavItemSelectedListener);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(onFloatClickListener);

        sessionSharedPreferences = getSharedPreferences(
                MyPreferences.PREFERENCE_SESSION.getKeyValue(), MODE_PRIVATE
        );
        userName = sessionSharedPreferences.getString(
                MyPreferences.SessionKeys.USERNAME_KEY.getKeyValue(),
                getResources().getString(R.string._user_name)
        );
        remember = sessionSharedPreferences.getBoolean(
                MyPreferences.SessionKeys.REMEMBER_KEY.getKeyValue(),
                false
        );

        String userMail = userName + getResources().getString(R.string._email_example);

        View view = navView.getHeaderView(0);
        // Set user name
        ((TextView)view.findViewById(R.id.txt_nav_user)).setText(userName);
        // Set user email
        ((TextView)view.findViewById(R.id.txt_nav_email)).setText(userMail);

        if (!remember) {
            showSnackbar(getResources().getString(R.string.remeber_disabled));
        }

        changeFragment(new AccountPreference());

    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void changeFragment(android.app.Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
