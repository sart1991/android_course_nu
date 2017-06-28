package com.exercises.sart1991.evaluacionfinal9.ui.activitys.main;

import android.support.v4.app.Fragment;

import com.exercises.sart1991.evaluacionfinal9.ui.base.MvpView;

/**
 * Created by sart1 on 6/27/2017.
 */

public interface MainMvpView extends MvpView {

    void showFragment(Fragment fragment);

    void requestPermissionsSafely(String[] permissions, int requestCode);

    boolean hasPermission(String permission);
}
