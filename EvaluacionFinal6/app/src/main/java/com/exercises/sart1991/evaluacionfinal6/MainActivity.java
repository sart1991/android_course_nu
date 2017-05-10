package com.exercises.sart1991.evaluacionfinal6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilUserLogin, tilPasswordLogin;
    private EditText editUserLogin, editPasswordLogin;
    private CheckBox checkRemember;
    private SharedPreferences sessionSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionSharedPreferences = getSharedPreferences(
                MyPreferences.PREFERENCE_SESSION.getKeyValue(),
                MODE_PRIVATE
        );
        if (sessionSharedPreferences
                .getBoolean(MyPreferences.SessionKeys.REMEMBER_KEY.getKeyValue(), false)) {
            startActivity(new Intent(this, UserActivity.class));
        }
        tilUserLogin = (TextInputLayout) findViewById(R.id.til_username);
        editUserLogin = tilUserLogin.getEditText();
        tilPasswordLogin = (TextInputLayout) findViewById(R.id.til_password);
        editPasswordLogin = tilPasswordLogin.getEditText();
        checkRemember = (CheckBox) findViewById(R.id.check_remember);
    }

    public void onClickButtonLogin(View view) {
        if ("".equals(editUserLogin.getText().toString())) {
            tilUserLogin.setError(getResources().getString(R.string.error_empty_field));
        } else if ("".equals(editPasswordLogin.getText().toString())) {
            tilPasswordLogin.setError(getResources().getString(R.string.error_empty_field));
        } else {
            sessionSharedPreferences.edit().putString(
                    MyPreferences.SessionKeys.USERNAME_KEY.getKeyValue(),
                    editUserLogin.getText().toString()
            ).putBoolean(
                    MyPreferences.SessionKeys.REMEMBER_KEY.getKeyValue(),
                    checkRemember.isChecked()
            ).apply();
            startActivity(new Intent(this, UserActivity.class));
        }
    }
}
