package com.exercises.sart1991.tablayoutconventions;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.exercises.sart1991.tablayoutconventions.adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager_main);
        if (viewPager != null) {
            viewPager.setAdapter( new TabAdapter(getSupportFragmentManager(), this));
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_main);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                Drawable icon = null;
                switch (i) {
                    case 0:
                        icon = ContextCompat.getDrawable(this, R.drawable.ic_book);
                        break;
                    case 1:
                        icon = ContextCompat.getDrawable(this, R.drawable.ic_videogame_asset);
                        break;
                    case 2:
                        icon = ContextCompat.getDrawable(this, R.drawable.ic_movie);
                        break;
                    case 3:
                        icon = ContextCompat.getDrawable(this, R.drawable.ic_music_note);
                        break;
                }

                if (tab != null) {
                    tab.setIcon(icon);
                    tab.setText("");
                }
            }
        }
    }
}
