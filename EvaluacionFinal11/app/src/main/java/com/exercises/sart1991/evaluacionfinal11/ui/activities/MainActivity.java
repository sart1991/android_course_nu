package com.exercises.sart1991.evaluacionfinal11.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal11.R;
import com.exercises.sart1991.evaluacionfinal11.utils.EVConstants;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int CODE_FOR_RESULT_SIGN_IN = 1;

    private LoginButton loginButton;
    private SignInButton signInButton;

    private GoogleApiClient googleApiClient;
    private CallbackManager facebookCallbackManager;

    private SharedPreferences preferences;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.exercises.sart1991.evaluacionfinal11.R.layout.activity_main);
        setUp();
        tryToGoContentActivity();
    }

    private void setUp() {
        bindViews();
        requestMyPermissions();
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

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.i(TAG, "onCurrentProfileChanged: name: " + currentProfile.getFirstName());
                preferences.edit().putString(EVConstants.EMAIL, currentProfile.getFirstName())
                                    .apply();
            }
        };
        profileTracker.startTracking();
    }

    private void bindViews() {
        loginButton = findViewById(R.id.loginButton_mainActivity_facebook);
        signInButton = findViewById(R.id.signIn_mainActivity_google);
    }

    private void requestMyPermissions() {
        int leer = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        Log.i(TAG, "requestMyPermissions: leer != PERMISSION_GRANTED : " + (leer != PackageManager.PERMISSION_GRANTED));
        if (leer != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.INTERNET},
                    43
            );
        } else {
            Snackbar.make(
                        signInButton,
                        R.string.internet_permission_granted,
                        BaseTransientBottomBar.LENGTH_LONG)
                    .show();
        }
    }

    private View.OnClickListener onClickSignInButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            signInGoogle();
        }
    };

    private void signInGoogle() {
        Log.i(TAG, "signInGoogle: starting");
        Intent singInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(singInIntent, CODE_FOR_RESULT_SIGN_IN);
        Log.i(TAG, "signInGoogle: leaving");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed: Error connecting with Google account, "
                + connectionResult.getErrorMessage());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: code == requestCode : " + (CODE_FOR_RESULT_SIGN_IN == requestCode));
        if (CODE_FOR_RESULT_SIGN_IN == requestCode) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.i(TAG, "onActivityResult: beforeHandle");
            handleGoogleSignIn(result);
            Log.i(TAG, "onActivityResult: afterHandle");
        } else {

            if (resultCode != RESULT_OK) {
                Snackbar snack = Snackbar.make(
                        signInButton,
                        "Error iniciando sesion",
                        BaseTransientBottomBar.LENGTH_LONG);
                snack.getView().setBackgroundColor(
                        getResources().getColor(android.R.color.holo_red_dark)
                );
                snack.show();
            }
        }
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleGoogleSignIn(GoogleSignInResult result) {
        Log.i(TAG, "handleGoogleSignIn: result: " + (result.isSuccess()));
        Log.i(TAG, "handleGoogleSignIn: resultCode: " + result.getStatus().getStatusCode());
        if (result.isSuccess()) {
            preferences.edit().putBoolean(EVConstants.GOOGLE_LOGIN, true).commit();
            Log.i(TAG, "handleGoogleSignIn: signinAccount: " + result.getSignInAccount());
            Log.i(TAG, "handleGoogleSignIn: name: " + result.getSignInAccount().getGivenName());
            Log.i(TAG, "handleGoogleSignIn: lastName: " + result.getSignInAccount().getFamilyName());
            Log.i(TAG, "handleGoogleSignIn: email: " + result.getSignInAccount().getEmail());
            if (result.getSignInAccount().getEmail() != null) {
                preferences.edit().putString(EVConstants.EMAIL, result.getSignInAccount().getEmail())
                        .apply();
            }
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
        //if (preferences.getBoolean(EVConstants.FACEBOOK_LOGIN, false) &&
            //preferences.getBoolean(EVConstants.GOOGLE_LOGIN, false)) {
            startActivity(new Intent(this, ContentActivity.class));
        //}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }
}
