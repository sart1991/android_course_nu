package com.exercises.sart1991.evaluacionfinal7.ui.activity.login;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.User;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BasePresenter;

/**
 * Created by sart1 on 5/22/2017.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {


    @Override
    public void onLoginClick(String userName, String password) {
        if (validateUser(userName, password)) {
            getMvpView().gotoMainActivity();
        }
    }

    private boolean validateUser(String userName, String password) {
        if ("".equals(userName)) {
            getMvpView().onError(R.string.login_errorEmptyField);
            getMvpView().onUserNameFieldError(R.string.login_emptyField);
            return false;
        }
        if ("".equals(password)) {
            getMvpView().onError(R.string.login_errorEmptyField);
            getMvpView().onPasswordFieldError(R.string.login_emptyField);
            return false;
        }

        User user = getDataManager().getUser(userName);
        if (user == null) {
            getMvpView().onError(R.string.login_errorUserDoesNotExists);
            getMvpView().onUserNameFieldError(R.string.login_tryAnotherUser);
            return false;
        }
        if(!password.equals(user.getPassword())) {
            getMvpView().onError(R.string.login_errorWrongPassword);
            getMvpView().onError(R.string.login_errorWrongPassword);
            return false;
        }
        return true;
    }

    @Override
    public void onNewUserClick() {
        getMvpView().showDialogForNewUser();
    }

    @Override
    public void onCancelDialogNewUser() {
        getMvpView().onNotify(R.string.login_notifyUserCanceled);
    }

    @Override
    public void onConfPasswordChanged(String password, String confPassword) {
        validateNewUserPassword(password, confPassword);
    }

    @Override
    public void onCreateNewUser(String userName, String password, String confPassword) {
        if (validateNewUser(userName, password, confPassword)) {
            getMvpView().makeNewDialogForNewUser();
            getDataManager().insertUser(new User(userName, confPassword));
            getMvpView().onSuccess(R.string.login_successUserCreated);
        }
    }

    private boolean validateNewUser(String userName, String password, String confPassword) {

        if ("".equals(userName) || "".equals(password) ) {
            getMvpView().onError(R.string.login_errorEmptyField);
            return false;
        }

        User user = getDataManager().getUser(userName);
        if (user != null) {
            getMvpView().onError(R.string.login_errorUserAlreadyExists);
            return false;
        }
        if(!validateNewUserPassword(password, confPassword)) {
            getMvpView().onError(R.string.login_errorPasswordDontMatch);
            return false;
        }
        return true;
    }

    private boolean validateNewUserPassword(String password, String confPassword) {
        if (!password.equals(confPassword)) {
            getMvpView().onConfPasswordFieldError(R.string.login_errorPasswordDontMatch);
            return false;
        }
        return true;
    }
}
