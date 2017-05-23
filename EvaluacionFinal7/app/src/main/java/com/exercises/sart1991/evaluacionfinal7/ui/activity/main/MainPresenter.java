package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BasePresenter;

/**
 * Created by sart1 on 5/19/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Override
    public void validateSession() {
        if(!isSessionActive()) {
            getMvpView().gotoLogin();
        }
    }

    @Override
    public void onWelcome() {

    }

    @Override
    public void onOptionItemClick(int itemId) {
        switch (itemId) {
            case R.id.item_mainOptions_logout:
                getDataManager().setLoginState(false);
                getMvpView().gotoLogin();
                break;
        }
    }
}
