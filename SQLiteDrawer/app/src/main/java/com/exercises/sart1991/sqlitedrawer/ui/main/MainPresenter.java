package com.exercises.sart1991.sqlitedrawer.ui.main;

import com.exercises.sart1991.sqlitedrawer.ui.base.BasePresenter;

/**
 * Created by sart1 on 5/16/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Override
    public void onDrawerOptionInsertClick() {
        getMvpView().showInsertFragment();
    }
}
