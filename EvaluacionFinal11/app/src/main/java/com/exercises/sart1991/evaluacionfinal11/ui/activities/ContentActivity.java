package com.exercises.sart1991.evaluacionfinal11.ui.activities;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.exercises.sart1991.evaluacionfinal11.repository.geofence.GeofenceTransitionIntentService;
import com.exercises.sart1991.evaluacionfinal11.utils.EVConstants;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = ContentActivity.class.getSimpleName();

    private FloatingActionButton fab;
    private TextView txtEmail;

    private SharedPreferences preferences;
    private ProfileTracker profileTracker;

    private Geofence mAndroidGeofence;
    private List<Geofence> geofences;
    private PendingIntent mGeofenceRequestIntent;
    private GoogleApiClient mGoogleApiCleint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setUp();
    }

    private void setUp() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        bindViews();
        //requestPermissions();

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

    private void createGoogleApiClient() {
        if (!availableGoogleApiServies()) {
            return;
        }
        mGoogleApiCleint = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                                                            .addConnectionCallbacks(this)
                                                            .addOnConnectionFailedListener(this)
                                                            .build();
        mGoogleApiCleint.connect();
        geofences = new ArrayList<>();

    }

    public void createGeofences() {

        Geofence geofence = new Geofence.Builder()
                .setRequestId(EVConstants.ANDROID_ID)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .setCircularRegion(EVConstants.ANDROID_LATITUDE, EVConstants.ANDROID_LONGITUDE, EVConstants.ANDROID_RADIUS)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
        geofences.add(geofence);
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestPermissions();
        mGeofenceRequestIntent = getGeofenceTransitionPendingIntent();
        LocationServices.GeofencingApi.addGeofences(mGoogleApiCleint, geofences, mGeofenceRequestIntent);

        //TODO: show message iniciando geofence
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private boolean availableGoogleApiServies() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == resultCode) {
            Snackbar.make(fab, "", BaseTransientBottomBar.LENGTH_LONG).show();
            return true;
        }
        Snackbar.make(fab, "Servicios de Google Play no disponibles", BaseTransientBottomBar.LENGTH_LONG).show();
        return false;
    }

    private PendingIntent getGeofenceTransitionPendingIntent() {
        Intent intent = new Intent(this, GeofenceTransitionIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
