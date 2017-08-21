package com.exercises.sart1991.evaluacionfinal10;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        SensorEventListener{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;
    private CameraPosition mCameraPosition;
    private LatLng mDefaultLocation = new LatLng(-34, 151);
    //private PolylineOptions mPolylineOptions;
    private Polyline mPolyline;
    private List<LatLng> mListPolyline = new ArrayList<>();
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1234;
    private static final float DEFAULT_ZOOM = 10.0f;

    private SensorManager mSensorManager;

    private FloatingActionButton fab;
    private Dialog mDialogTemperature;
    private TextView txtTempC;
    private TextView txtTempF;
    private TextView txtTempK;

    private static final int PLACE_PICKER_REQUEST = 1;
    private static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();



        setUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String message = String.format("Place: %s", place.getName());
                Snackbar.make(fab, message, Snackbar.LENGTH_LONG).show();
                addPolylinePoint(place.getLatLng());
                mPolyline.setPoints(mListPolyline);
                mMap.addMarker(new MarkerOptions()
                        .position(place.getLatLng())
                        .title(place.getName().toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                );
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 5));
            }
        }
    }

    private void setUp() {
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(onFabClick);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sensor != null) {
            Snackbar.make(fab, R.string.main_snack_temperatureSensor, Snackbar.LENGTH_LONG).show();
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
            setUpDialogTemperature();
        } else {
            Snackbar.make(fab, R.string.main_snack_noTemperatureSensor, Snackbar.LENGTH_LONG).show();
        }
        //setUpDialogTemperature();
    }

    private void setUpDialogTemperature() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_temperature, null, false);
        txtTempC = (TextView)view.findViewById(R.id.textView_dialogTemperature_c);
        txtTempK = (TextView)view.findViewById(R.id.textView_dialogTemperature_k);
        txtTempF = (TextView)view.findViewById(R.id.textView_dialogTemperature_f);
        alertBuilder.setView(view);
        alertBuilder.setTitle(getString(R.string.mainDialogTemperature_Title));
        alertBuilder.setPositiveButton(R.string.mainDialogTemperature_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mDialogTemperature = alertBuilder.create();
    }

    private void addPolylinePoint(LatLng point) {
        mListPolyline.add(point);
    }

    FloatingActionButton.OnClickListener onFabClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }
    };

    private void showDialogTemperature() {
        mDialogTemperature.show();
    }

    private AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.item_mainOption_temp:
                if (mDialogTemperature != null)
                    showDialogTemperature();
                else {
                    Snackbar.make(fab, R.string.main_snack_noTemperatureSensor,
                            Snackbar.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        PolylineOptions polylineOptions = new PolylineOptions()
                .width(4).color(getResources().getColor(R.color.colorPrimary));
        mPolyline = mMap.addPolyline(polylineOptions);
        updateLocationUI();
        getDeviceLocation();
    }



    private void getDeviceLocation() {
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mLastKnownLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
        }

        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            LatLng location =
                    new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
            mCameraPosition = new CameraPosition(location, 0, 0, 0);
            mMap.addMarker(new MarkerOptions().position(location)
                    .title(getString(R.string.main_markerCurrentLocation_title)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, DEFAULT_ZOOM));
            mListPolyline.add(location);
        } else {
            Log.d(TAG, "Using default location");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            mMap.setMyLocationEnabled(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) { return; }

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        NumberFormat format = new DecimalFormat("#0.0");
        double celcius = event.values[0];
        double fahrenheit = (event.values[0] * (9/5)) + 32;
        double kelvin = event.values[0] + 273.15;
        Log.i(TAG, "onSensorChanged: values: " + event.values[0]);
        if (txtTempC != null) {
            txtTempC.setText(format.format(celcius));
            txtTempF.setText(format.format(fahrenheit));
            txtTempK.setText(format.format(kelvin));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
