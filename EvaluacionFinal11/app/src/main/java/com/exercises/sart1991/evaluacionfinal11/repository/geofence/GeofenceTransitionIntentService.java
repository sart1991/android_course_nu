package com.exercises.sart1991.evaluacionfinal11.repository.geofence;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
        if (geoEvent.hasError()) {
            //TODO: Show error
        } else  {
            int transitionType = geoEvent.getGeofenceTransition();
            if (Geofence.GEOFENCE_TRANSITION_ENTER == transitionType) {
                mGoogleApiClient.blockingConnect(EVConstants.CONNECTION_TIME_OUT_MS,
                                                 TimeUnit.MICROSECONDS);
                String triggerGeofence = geoEvent.getTriggeringGeofences().get(0).getRequestId();

                //TODO: mostrar mensaje entrada al usuario

                mGoogleApiClient.disconnect();
            } else if (Geofence.GEOFENCE_TRANSITION_EXIT == transitionType) {
                mGoogleApiClient.blockingConnect(EVConstants.CONNECTION_TIME_OUT_MS,
                        TimeUnit.MICROSECONDS);

                //TODO: mostrar mensaje salida al usuario

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
