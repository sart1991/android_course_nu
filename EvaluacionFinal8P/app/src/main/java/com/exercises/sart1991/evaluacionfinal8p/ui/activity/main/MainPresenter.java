package com.exercises.sart1991.evaluacionfinal8p.ui.activity.main;

import android.util.Log;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BasePresenter;

/**
 * Created by sart1 on 6/7/2017.
 */

public class MainPresenter<V extends MainMvpView>
        extends BasePresenter<V> implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Override
    public void welcome() {
        getDataManager().checkProfessorLogin(listenLogin);
    }

    @Override
    public boolean clickSignOut(int itemId) {
        switch (itemId) {
            case R.id.item_mainOptions_signOut:
                getDataManager().setToken("");
                getMvpView().gotoLogin();
                return true;
        }
        return false;
    }

    private ApiSchoolHelper.ListenRequest<Professor> listenLogin = new ApiSchoolHelper.ListenRequest<Professor>() {
        @Override
        public void onSuccess(Professor result) {
            Log.i(TAG, "onSuccess: login: " + result);
        }

        @Override
        public void onError() {
            Log.i(TAG, "onError: error");
            getMvpView().gotoLogin();
        }
    };
}
