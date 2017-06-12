package com.exercises.sart1991.evaluacionfinal8s.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.exercises.sart1991.evaluacionfinal8s.R;
import com.exercises.sart1991.evaluacionfinal8s.ui.activity.main.MainActivity;
import com.exercises.sart1991.evaluacionfinal8s.ui.base.BaseActivity;


public class LoginActivity extends BaseActivity implements LoginMvpView {

    private static final LoginMvpPresenter<LoginMvpView> PRESENTER = new LoginPresenter<>();

    private TextInputLayout tilEmail, tilPassword;
    private EditText editEmail, editPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PRESENTER.onAttach(this);
        setupComponents();
    }

    @Override
    protected void setupComponents() {
        tilEmail = (TextInputLayout) findViewById(R.id.til_login_email);
        tilPassword = (TextInputLayout) findViewById(R.id.til_login_password);
        editEmail = (EditText) findViewById(R.id.editText_login_email);
        editEmail.addTextChangedListener(textWatcherValidationEmail);
        editPassword = (EditText) findViewById(R.id.editText_login_password);
        editPassword.addTextChangedListener(textWatcherValidationPassword);
        buttonLogin = (Button) findViewById(R.id.button_login_login);
        buttonLogin.setOnClickListener(onClickButtonLogin);
    }

    @Override
    public void errorEmail() {
        tilEmail.setError(" ");
    }

    @Override
    public void errorPassword() {
        tilPassword.setError(" ");
    }

    private TextWatcher textWatcherValidationEmail = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tilEmail.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {
            PRESENTER.validateEmail(s.toString());
        }
    };

    private TextWatcher textWatcherValidationPassword = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tilPassword.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private View.OnClickListener onClickButtonLogin = new OnClickListener() {
        @Override
        public void onClick(View v) {
            PRESENTER.clickButtonLogin(
                    editEmail.getText().toString(), editPassword.getText().toString()
            );
        }
    };

    @Override
    public void gotoMain() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}

