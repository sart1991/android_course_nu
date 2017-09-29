package com.exercises.sart1991.evaluacionfinal11.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal11.R;
import com.exercises.sart1991.evaluacionfinal11.utils.EVConstants;
import com.facebook.Profile;
import com.facebook.ProfileTracker;

public class ContentActivity extends AppCompatActivity {

    private static final String TAG = ContentActivity.class.getSimpleName();

    private FloatingActionButton fab;
    private TextView txtEmail;

    private SharedPreferences preferences;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setUp();
    }

    private void setUp() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        bindViews();
        requestPermissions();
        fab.setOnClickListener(fabListener);
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.i(TAG, "onCurrentProfileChanged: name: " + currentProfile.getFirstName());
                preferences.edit().putString(EVConstants.EMAIL, currentProfile.getFirstName())
                        .apply();
            }
        };
        profileTracker.startTracking();
        txtEmail.setText(preferences.getString(EVConstants.EMAIL, "nn"));
    }

    private void bindViews() {
        fab = findViewById(R.id.fab);
        txtEmail = findViewById(R.id.textView_contentActivity_email);
    }

    private void requestPermissions() {
        int leer = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (leer != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    43
            );
        } else {
            Snackbar.make(
                    fab,
                    R.string.location_permission_granted,
                    BaseTransientBottomBar.LENGTH_LONG)
                    .show();
        }
    }

    private FloatingActionButton.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gotoContactsActivity();
        }
    };

    private void gotoContactsActivity() {
        startActivity(new Intent(this, ContactsActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }
}
