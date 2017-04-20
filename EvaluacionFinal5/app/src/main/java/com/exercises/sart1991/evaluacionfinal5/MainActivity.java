package com.exercises.sart1991.evaluacionfinal5;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.exercises.sart1991.evaluacionfinal5.usable.CustomTheme;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomTheme.onActivityCreateSetTheme(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        int theme = R.style.AppTheme;
        switch (itemId) {
            case R.id.drawer_facebook:
                theme = R.style.AppTheme_Facebook;
                break;
            case R.id.drawer_instagram:
                theme = R.style.AppTheme_Instagram;
                break;
            case R.id.drawer_gplus:
                theme = R.style.AppTheme_Gplus;
                break;
            case R.id.drawer_twitter:
                theme = R.style.AppTheme_Twitter;
                break;
        }
        CustomTheme.changeToTheme(this, theme);
        return true;
    }
}
