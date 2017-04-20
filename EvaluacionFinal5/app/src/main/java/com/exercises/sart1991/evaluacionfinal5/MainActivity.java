package com.exercises.sart1991.evaluacionfinal5;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.exercises.sart1991.evaluacionfinal5.adapter.PagerAdapter;
import com.exercises.sart1991.evaluacionfinal5.usable.CustomTheme;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomTheme.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle(CustomTheme.getSubtitle());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(CustomTheme.getTheme()).setChecked(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(CustomTheme.getArrayOfIconTabs()[1][i]);
        }
        if (CustomTheme.getTheme() == CustomTheme.THEME_DEFAULT) {
            tabLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        int itemId = item.getItemId();
        int theme = CustomTheme.THEME_DEFAULT;
        switch (itemId) {
            case R.id.drawer_facebook:
                theme = CustomTheme.THEME_FACEBOOK;
                break;
            case R.id.drawer_instagram:
                theme = CustomTheme.THEME_INSTAGRAM;
                break;
            case R.id.drawer_gplus:
                theme = CustomTheme.THEME_GPLUS;
                break;
            case R.id.drawer_twitter:
                theme = CustomTheme.THEME_TWITTER;
                break;
        }
        CustomTheme.changeToTheme(this, theme);
        return true;
    }
}
