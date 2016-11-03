package com.exercise.nextu.dinamicfragmentsmultiactivityl3;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Admin on 11/3/2016.
 */

public class DiasProgramacion extends FragmentPagerAdapter {

    private static final int PAGES = 3;

    public DiasProgramacion(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return new ProgramacionAyerFragment();
            case 1:
                return new ProgramacionHoyFragment();
            default:
                return new ProgramacionMananaFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGES;
    }
}
