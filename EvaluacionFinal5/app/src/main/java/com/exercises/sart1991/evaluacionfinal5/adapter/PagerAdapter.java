package com.exercises.sart1991.evaluacionfinal5.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.exercises.sart1991.evaluacionfinal5.BasicFragment;
import com.exercises.sart1991.evaluacionfinal5.usable.CustomTheme;

/**
 * Created by sart1 on 4/20/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int[][] tabsInfo;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        tabsInfo = CustomTheme.getArrayOfIconTabs();
    }

    @Override
    public Fragment getItem(int position) {
        return BasicFragment.newInstance(tabsInfo[0][position], tabsInfo[1][position]);
    }

    @Override
    public int getCount() {
        return tabsInfo[0].length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
