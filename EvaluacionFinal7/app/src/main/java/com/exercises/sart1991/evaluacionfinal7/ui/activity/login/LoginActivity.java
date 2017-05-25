package com.exercises.sart1991.evaluacionfinal7.ui.activity.login;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.ui.activity.main.MainActivity;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final LoginMvpPresenter<LoginMvpView> PRESENTER = new LoginPresenter<>();

    private TextInputLayout tilUserName, tilPassword, tilNewUserConfPassword;
    private EditText editUserName, editPassword;
    private Dialog dialogNewUser;

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
        dialogNewUser = makeDialogForNewUser();

        //Bind with events
        editUserName.addTextChangedListener(textWatcherUserFields);
        editPassword.addTextChangedListener(textWatcherUserFields);
    }

    public void onClickLogin(View view) {
        PRESENTER.onLoginClick(
                editUserName.getText().toString(),
                editPassword.getText().toString()
        );
    }

    public void onClickSignUp(View view) {
        PRESENTER.onNewUserClick();
    }

    @Override
    public void onUserNameFieldError(int resId) {
        tilUserName.setError(getString(resId));
    }

    @Override
    public void onPasswordFieldError(int resId) {
        tilPassword.setError(getString(resId));
    }

    @Override
    public void showDialogForNewUser() {
        dialogNewUser.show();
    }

    @Override
    public void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private TextWatcher textWatcherUserFields = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.i(TAG, "onTextChanged: " + s + " start: " + start + " before: " + before + " count: " + count);
            tilUserName.setErrorEnabled(false);
            tilPassword.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private Dialog makeDialogForNewUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogNewUser_title);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_newuser, null);
        final EditText editUserName = (EditText) view.findViewById(R.id.editText_dialogNewUser_userName);
        final EditText editPassword = (EditText) view.findViewById(R.id.editText_dialogNewUser_password);
        final EditText editCPassword = (EditText) view.findViewById(R.id.editText_dialogNewUser_confPassword);
        tilNewUserConfPassword = (TextInputLayout) view.findViewById(R.id.til_dialogNewUser_confPassword);

        TextWatcher textWatcherNewUserPassword = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNewUserConfPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                PRESENTER.onConfPasswordChanged(
                        editPassword.getText().toString(),
                        editCPassword.getText().toString()
                );
            }
        };
        editCPassword.addTextChangedListener(textWatcherNewUserPassword);

        builder.setView(view);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                PRESENTER.onCancelDialogNewUser();
            }
        });
        builder.setNegativeButton(getString(R.string.dialogNewUser_negativeButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(getString(R.string.dialogNewUser_positiveButton), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PRESENTER.onCreateNewUser(
                        editUserName.getText().toString(),
                        editPassword.getText().toString(),
                        editCPassword.getText().toString()
                );
            }
        });
        return builder.create();
    }

    @Override
    public void onConfPasswordFieldError(int resId) {
        tilNewUserConfPassword.setError(getString(resId));
    }

    @Override
    public void cleanDialogNewUserData() {
        dialogNewUser = makeDialogForNewUser();
    }

    @Override
    public void onBackPressed() {
        Intent intentHome = new Intent(Intent.ACTION_MAIN);
        intentHome.addCategory(Intent.CATEGORY_HOME);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentHome);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
    }
}
