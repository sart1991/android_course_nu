package com.super_test.thesupertest;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SergioAlejandro on 3/09/2016.
 */
public class LoginValidation {

    private Context context;

    public LoginValidation(Context context) {
        this.context = context;
    }

    public boolean authenticate(JSONObject jsonObject, View view, EditText txtUserName, EditText txtPassword) {

        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();

        if (username.isEmpty()) {
            txtUserName.setError(context.getString(R.string.error_empty_field));
            txtUserName.requestFocus();
            Snackbar.make(view, R.string.empty_user_name_field, Snackbar.LENGTH_LONG).show();
            return false;
        } else if (password.isEmpty()) {
            txtPassword.setError(context.getString(R.string.error_empty_field));
            txtPassword.requestFocus();
            Snackbar.make(view, R.string.empty_password_field, Snackbar.LENGTH_LONG).show();
            return false;
        } else {
            try {
                jsonObject.get("username");
                return true;
            } catch (JSONException jsone) {
                txtUserName.setError(context.getString(R.string.error_info_field));
                txtPassword.setError(context.getString(R.string.error_info_field));
                txtPassword.requestFocus();
                Snackbar.make(view, "Wrong user name or password info.", Snackbar.LENGTH_LONG).show();
                return false;
            }
        }
    }
}
