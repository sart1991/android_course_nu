package com.exercises.sart1991.evaluacionfinal7.ui.activity.login;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final LoginMvpPresenter<LoginMvpView> PRESENTER = new LoginPresenter<>();

    private TextInputLayout tilUserName, tilPassword;
    private EditText editUserName, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PRESENTER.onAttach(this);
        initializeComponents();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void initializeComponents() {
        tilUserName = (TextInputLayout) findViewById(R.id.til_login_userName);
        tilPassword = (TextInputLayout) findViewById(R.id.til_login_password);
        editUserName = tilUserName.getEditText();
        editPassword = tilPassword.getEditText();
    }

    public void onClickLogin(View view) {
        PRESENTER.validateUserName(editUserName.getText().toString());
        PRESENTER.validatePassword(editPassword.getText().toString());
    }

    public void onClickSignUp(View view) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}
