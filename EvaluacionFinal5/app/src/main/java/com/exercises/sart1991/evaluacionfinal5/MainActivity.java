package com.exercises.sart1991.evaluacionfinal5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        View headerNav = navigationView.getHeaderView(0);
        ConstraintLayout headerNavLayout = (ConstraintLayout) headerNav.findViewById(R.id.nav_header_container);
        headerNavLayout.setBackgroundResource(CustomTheme.getPrimaryColor());

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.option_facebook:
                goToWebVersion(getResources().getString(R.string.url_facebook), R.color.colorPrimaryF);
                break;
            case R.id.option_instagram:
                goToWebVersion(getResources().getString(R.string.url_instagram), R.color.colorPrimaryI);
                break;
            case R.id.option_gplus:
                goToWebVersion(getResources().getString(R.string.url_gplus), R.color.colorPrimaryG);
                break;
            case R.id.option_twitter:
                goToWebVersion(getResources().getString(R.string.url_twitter), R.color.colorPrimaryT);
                break;
            case R.id.option_share:
                makeCheckListAlertDialog().show();
                break;
            case R.id.option_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToWebVersion(String url, int color) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, color));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    private Dialog makeCheckListAlertDialog() {
        final String[] shareTitles = getResources().getStringArray(R.array.share_checklist);
        final boolean[] shareSelection = new boolean[shareTitles.length];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.checklist_title);

        builder.setMultiChoiceItems(shareTitles, shareSelection, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        builder.setPositiveButton(R.string._share, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                String selection = "";
                for (int i = 0; i < shareSelection.length; i++) {
                    if (shareSelection[i]) {
                        selection += shareTitles[i] + " \n";
                    }
                }
                makeAlertDialog(selection).show();
            }
        });
        return builder.create();
    }

    private Dialog makeAlertDialog(final String selection) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmation_title);
        builder.setMessage(R.string.confirmation_content);
        builder.setPositiveButton(R.string._yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(
                        getApplicationContext(),
                        getResources().getString(R.string.toast_alert_start) + selection,
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.setNeutralButton(R.string._no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(
                        getApplicationContext(),
                        getResources().getString(R.string.toast_alert_start) +
                        getResources().getString(R.string._none),
                        Toast.LENGTH_LONG).show();
            }
        });
        return builder.create();
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
