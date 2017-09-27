package com.exercises.sart1991.evaluacionfinal11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.internal.*;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int CODE_FOR_RESULT_SIGN_IN = 51;

    private LoginButton loginButton;
    private SignInButton signInButton;

    private GoogleApiClient googleApiClient;
    private CallbackManager facebookCallbackManager;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
        tryToGoContentActivity();
    }

    private void setUp() {
        bindViews();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
        TextView txtSignInButton = (TextView) signInButton.getChildAt(0);
        txtSignInButton.setText(getString(R.string.mainActivity_loginButton));
        signInButton.setOnClickListener(onClickSignInButton);
        facebookCallbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(facebookCallbackManager, facebookCallback);
    }

    private void bindViews() {
        loginButton = findViewById(R.id.loginButton_mainActivity_facebook);
        signInButton = findViewById(R.id.signIn_mainActivity_google);
    }

    private View.OnClickListener onClickSignInButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            signInGoogle();
        }
    };

    private void signInGoogle() {
        Intent singInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(singInIntent, CODE_FOR_RESULT_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed: Error connecting with Google account, "
                + connectionResult.getErrorMessage());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (CODE_FOR_RESULT_SIGN_IN == requestCode) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleGoogleSignIn(result);
            }
        }
    }

    private void handleGoogleSignIn(GoogleSignInResult result) {
        if (result.isSuccess()) {
            preferences.edit().putBoolean(EVConstants.GOOGLE_LOGIN, true).commit();
            preferences.edit().putString(EVConstants.EMAIL, result.getSignInAccount().getEmail())
                              .commit();
            tryToGoContentActivity();
        }
    }

    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            preferences.edit().putBoolean(EVConstants.FACEBOOK_LOGIN, true).commit();
            tryToGoContentActivity();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    private void tryToGoContentActivity() {
        if (preferences.getBoolean(EVConstants.FACEBOOK_LOGIN, false) &&
            preferences.getBoolean(EVConstants.GOOGLE_LOGIN, false)) {
            startActivity(new Intent(this, ContentActivity.class));
        }
    }
}
