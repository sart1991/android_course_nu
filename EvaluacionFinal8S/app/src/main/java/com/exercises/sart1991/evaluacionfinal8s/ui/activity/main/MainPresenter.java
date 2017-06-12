package com.exercises.sart1991.evaluacionfinal8s.ui.activity.main;

import android.os.Handler;
import android.app.LoaderManager;
import android.util.Log;

import com.exercises.sart1991.evaluacionfinal8s.R;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Task;
import com.exercises.sart1991.evaluacionfinal8s.data.provider.ProviderLoader;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.BasePresenter;

import java.util.List;

/**
 * Created by sart1 on 6/11/2017.
 */

public class MainPresenter<V extends MainMvpView>
        extends BasePresenter<V> implements MainMvpPresenter<V> {


    private static final String TAG = MainPresenter.class.getSimpleName();

    private Student student;

    @Override
    public void welcome() {
        getDataManager().checkStudentLogin(listenLogin);
    }

    @Override
    public void getProviderData(LoaderManager loaderManager) {
        ProviderLoader loader = getDataManager().getLoaderData(new ProviderLoader.Callback() {
            @Override
            public void onLoaderFinished(List<Task> result) {
                Log.i(TAG, "onLoaderFinished: " + result);
                getMvpView().setListTasks(result);
            }

            @Override
            public void onLoaderError() {

            }
        });
        getDataManager().provideStudentId(student.getId());
        loaderManager.initLoader(1, null, loader);
    }

    @Override
    public void clickOptionsMenu(int itemId) {
        switch (itemId) {
            case R.id.item_main_signOutOption:
                getDataManager().setToken("");
                getMvpView().gotoLogin();
                break;
        }
    }

    private ApiSchoolHelper.ListenRequest<Student> listenLogin = new ApiSchoolHelper.ListenRequest<Student>() {
        @Override
        public void onSuccess(Student result) {
            getMvpView().setToolbarSubtitle(result.getName());
            student = result;
            Log.i(TAG, "onSuccess: " + result);
            getMvpView().callProviderData();
        }

        @Override
        public void onError() {
            if (getDataManager().getToken() == null || "".equals(getDataManager().getToken())) {
                getMvpView().gotoLogin();
                return;
            }
            if(!getMvpView().checkInternetConnection()) {
                getMvpView().onError(R.string.error_noInterentConnection, null);
                return;
            } else {
                getMvpView().onError(R.string.error_serverError, null);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMvpView().gotoLogin();
                    }
                }, 2000);
                return;
            }
        }
    };
}
