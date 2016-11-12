package com.super_test.thesupertest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //private TextView textTest;
    private EditText txtUserName;
    private EditText txtPassword;
    private String userName;
    private String password;
    private LoginValidation loginValidate;
    private boolean authentication;
    private Intent intentToProductActivity;
    private SharedPreferences sharedPreferences;
    boolean session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Intent to ProductsActivity
        intentToProductActivity = new Intent(this, ProductsActivity.class);

        //Checking for session state
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        session = sharedPreferences.getBoolean("session", false);
        if (session) {
            startActivity(intentToProductActivity);
        }
        //textTest = (TextView)findViewById(R.id.textTest);
        //textTest.setText("no");
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        loginValidate = new LoginValidation(this);
    }

    // Listen to the log in button click
    public void logInClicked(final View view) {
        userName = txtUserName.getText().toString();
        password = txtPassword.getText().toString();
        final RequestCall requestCall = new RequestCall();
        RequestQueue queue = requestCall.doRequest(this, userName, password);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<JSONObject>() {
            @Override
            public void onRequestFinished(Request<JSONObject> request) {
                //textTest.setText("yes, " + requestCall.getJson().toString());
                authentication = loginValidate.authenticate(requestCall.getJson(), view, txtUserName, txtPassword);
                // SharedPreferences save authentication variable
                if (authentication) {
                    startActivity(intentToProductActivity);
                    sharedPreferences.edit().putBoolean("session", authentication).apply();
                }
            }
        });
        View focusedView = this.getCurrentFocus();
        if (focusedView!=null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}
