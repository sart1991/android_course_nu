package com.exercises.sart1991.evaluacionfinal11.repository.geofence;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.exercises.sart1991.evaluacionfinal11.utils.EVConstants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationServices;

import java.util.concurrent.TimeUnit;

/**
 * Created by sart1 on 9/29/2017.
 */

public class GeofenceTransitionIntentService extends IntentService implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String  TAG = GeofenceTransitionIntentService.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;


    public GeofenceTransitionIntentService() {
        super(GeofenceTransitionIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                                                            .addConnectionCallbacks(this)
                                                            .addOnConnectionFailedListener(this)
                                                            .build();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geoEvent = GeofencingEvent.fromIntent(intent);
        if (geoEvent == null || geoEvent.hasError()) {
            Log.i(TAG, "onHandleIntent: mensaje error");
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        } else  {
            int transitionType = geoEvent.getGeofenceTransition();
            if (Geofence.GEOFENCE_TRANSITION_ENTER == transitionType) {
                mGoogleApiClient.blockingConnect(EVConstants.CONNECTION_TIME_OUT_MS,
                                                 TimeUnit.MICROSECONDS);
                String triggerGeofence = geoEvent.getTriggeringGeofences().get(0).getRequestId();

                Log.i(TAG, "onHandleIntent: mensaje entrada");
                Toast.makeText(this, "Entrada", Toast.LENGTH_LONG).show();

                mGoogleApiClient.disconnect();
            } else if (Geofence.GEOFENCE_TRANSITION_EXIT == transitionType) {
                mGoogleApiClient.blockingConnect(EVConstants.CONNECTION_TIME_OUT_MS,
                        TimeUnit.MICROSECONDS);

                Log.i(TAG, "onHandleIntent: mensaje salida");
                Toast.makeText(this, "Salida", Toast.LENGTH_LONG).show();

                mGoogleApiClient.disconnect();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
