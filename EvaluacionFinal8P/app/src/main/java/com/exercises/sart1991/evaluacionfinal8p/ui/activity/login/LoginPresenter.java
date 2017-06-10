package com.exercises.sart1991.evaluacionfinal8p.ui.activity.login;

import android.util.Log;
import android.util.Patterns;

import com.exercises.sart1991.evaluacionfinal8p.R;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.ApiSchoolHelper;
import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Professor;
import com.exercises.sart1991.evaluacionfinal8p.ui.base.BasePresenter;

/**
 * Created by sart1 on 6/9/2017.
 */

public class LoginPresenter<V extends LoginMvpView>
        extends BasePresenter<V> implements LoginMvpPresenter<V> {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    @Override
    public void clickButtonLogin(String email, String password) {
//        getDataManager().testApiSchool(listenLogin);
        getDataManager().getTokenProfessor(new Professor(email, password), listenLogin);
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
            Log.i(TAG, "onSuccess: testApiSchool: " + result);
            getDataManager().setToken(result);
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
