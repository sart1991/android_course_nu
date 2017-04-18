package com.exercises.sart1991.tablayoutconventions.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.exercises.sart1991.tablayoutconventions.BooksFragment;
import com.exercises.sart1991.tablayoutconventions.GamesFragment;
import com.exercises.sart1991.tablayoutconventions.MoviesFragment;
import com.exercises.sart1991.tablayoutconventions.MusicFragment;
import com.exercises.sart1991.tablayoutconventions.R;

/**
 * Created by Sergio Rojas on 4/17/2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BooksFragment();
            case 1:
                return new GamesFragment();
            case 2:
                return new MoviesFragment();
        }
        return new MusicFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return context.getString(R.string.title_books);
            case 1:
                return  context.getString(R.string.title_games);
            case 2:
                return context.getString(R.string.title_movies);
            case 3:
                return context.getString(R.string.title_music);
        }

        return super.getPageTitle(position);
    }
}
