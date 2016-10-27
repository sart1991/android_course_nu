package com.example.sergioalejandro.evfinal2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Intent intent;
    private final String NAME = "nextu";
    private String PASSWORD = "1234";
    private EditText editTextName;
    private EditText editTextPassword;
    private String errorField;
    private String errorEmptyField;
    private String errorWrongField;
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle(getResources().getString(R.string.string_activity_login_title));
        intent = new Intent();
        editTextName = (EditText) findViewById(R.id.edit_txt_name);
        editTextPassword = (EditText) findViewById(R.id.edit_txt_password);
        errorField = getResources().getString(R.string.string_error);
        errorEmptyField = getResources().getString(R.string.string_error_empty_field);
        errorWrongField = getResources().getString(R.string.string_error_wrong_field);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    public void onLoginButtonClicked(View view) {
        intent.setClass(this, GalleryActivity.class);
        if (isFieldEmpty(editTextName, editTextPassword)) {
            errorEditText(errorEmptyField);
        } else if (evaluateFieldContent(editTextName, NAME) || evaluateFieldContent(editTextPassword, PASSWORD)) {
            startActivity(intent);
        } else {
            errorEditText(errorWrongField);
        }

    }

    public void onForgetButtonClicked(View view) {
        Snackbar snackbar = Snackbar.make(view, "Do you want to know your data?", Snackbar.LENGTH_LONG);
        snackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Name: " + NAME + " | Pass: " + PASSWORD, Toast.LENGTH_LONG).show();
            }
        });
        snackbar.show();
    }

    private boolean isFieldEmpty(EditText... editText) {
        for (EditText et: editText) {
            if (et.getText().toString().trim().equalsIgnoreCase("")) {
                return true;
            }
        }
        return false;
    }

    private boolean evaluateFieldContent(EditText editText, String string) {
        if (editText.getText().toString().equalsIgnoreCase(string)) {
            return true;
        }
        return false;
    }

    private void errorEditText(String message) {
        editTextName.setError(errorField + " " + message);
        editTextPassword.setError(errorField+ " " + message);
    }
}
