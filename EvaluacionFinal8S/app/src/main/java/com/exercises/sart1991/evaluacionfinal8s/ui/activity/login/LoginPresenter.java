package com.exercises.sart1991.evaluacionfinal8s.ui.activity.login;

import android.util.Log;
import android.util.Patterns;

import com.exercises.sart1991.evaluacionfinal8s.R;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8s.data.apischool.model.Student;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.BasePresenter;

/**
 * Created by sart1 on 6/11/2017.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    @Override
    public void clickButtonLogin(String email, String password) {
        getDataManager().getTokenStudent(new Student(email, password), listenLogin);
    }

    @Override
    public void validateEmail(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getMvpView().errorEmail();
        }
    }

    private ApiSchoolHelper.ListenRequest<String> listenLogin
            = new ApiSchoolHelper.ListenRequest<String>() {
        @Override
        public void onSuccess(String result) {
            getDataManager().setToken(result);
            Log.i(TAG, "onSuccess: login: token: " + getDataManager().getToken());
            getMvpView().gotoMain();
        }

        @Override
        public void onError() {
            getMvpView().errorEmail();
            getMvpView().errorPassword();
            getMvpView().onError(R.string.login_errorEmailOrPasswordWrong, null);
        }
    };
}
