package com.exercises.sart1991.evaluacionfinal11.ui.activities;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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
import com.exercises.sart1991.evaluacionfinal11.repository.geofence.GeofenceTransitionsIntentService;
import com.exercises.sart1991.evaluacionfinal11.utils.EVConstants;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {

    private static final String TAG = ContentActivity.class.getSimpleName();

    private FloatingActionButton fab;
    private TextView txtEmail;

    private SharedPreferences preferences;
    private ProfileTracker profileTracker;

    private GeofencingClient mGeofencingClient;
    private List<Geofence> mGeofencesList;
    private PendingIntent mGeofencePendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setUp();
        requestMyPermissions();
        createGeofences();
        assignGeofencing();
    }

    private void setUp() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        bindViews();
        mGeofencingClient = LocationServices.getGeofencingClient(this);
        mGeofencesList = new ArrayList<>();
        //requestMyPermissions();

        fab.setOnClickListener(fabListener);
        /*profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.i(TAG, "onCurrentProfileChanged: name: " + currentProfile.getFirstName());
                preferences.edit().putString(EVConstants.EMAIL, currentProfile.getFirstName())
                        .apply();
            }
        };
        profileTracker.startTracking();*/
        txtEmail.setText(preferences.getString(EVConstants.EMAIL, "nn"));
    }

    private void bindViews() {
        fab = findViewById(R.id.fab);
        txtEmail = findViewById(R.id.textView_contentActivity_email);
    }

    private void requestMyPermissions() {
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

    public void createGeofences() {

        Geofence geofence = new Geofence.Builder()
                .setRequestId(EVConstants.ANDROID_ID)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .setCircularRegion(EVConstants.ANDROID_LATITUDE, EVConstants.ANDROID_LONGITUDE, EVConstants.ANDROID_RADIUS)
                .setExpirationDuration(600000)
                .build();
        mGeofencesList.add(geofence);
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

    private PendingIntent getGeofencePendingIntent() {
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }

        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        mGeofencePendingIntent = PendingIntent.getService(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return mGeofencePendingIntent;
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofencesList);
        return builder.build();
    }

    private void assignGeofencing() {
        int leer = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (leer != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    43
            );
        }
        mGeofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                         .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {
                                 Snackbar.make(fab, "Servicio de geofence activo", BaseTransientBottomBar.LENGTH_LONG).show();
                             }
                         })
                         .addOnFailureListener(this, new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Log.e(TAG, "onFailure: ", e);
                                 Snackbar.make(fab, "Error en servicio de geofence", BaseTransientBottomBar.LENGTH_LONG).show();
                             }
                         });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //profileTracker.stopTracking();
    }
}
