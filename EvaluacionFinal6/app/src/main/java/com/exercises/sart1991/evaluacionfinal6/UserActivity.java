package com.exercises.sart1991.evaluacionfinal6;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.exercises.sart1991.evaluacionfinal6.Adapter.CardRecyclerAdapter;
import com.exercises.sart1991.evaluacionfinal6.model.Vehicle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements ParkingFragment.OnFragmentInteractionListener{

    private static final String TAG = UserActivity.class.getName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private SharedPreferences sessionSharedPreferences;
    private String userName;
    private boolean remember;
    private ParkingFragment parkingFragment;
    private AccountPreference accountPreference;
    private AlertDialog alertForRegister;
    private List<Vehicle> mVehicleList;
    private CardRecyclerAdapter recyclerAdapter;
    private String mRegistrationNumber;
    private String mClientId;

    private String fileName = "vehicles.obj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeComponents();
    }

    private NavigationView.OnNavigationItemSelectedListener onNavItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.drawer_parking) {
                        changeFragment(parkingFragment);
                    } else {
                        changeFragment(accountPreference);
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            };


    @Override
    public void onBackPressed() {
        if (!remember) super.onBackPressed();
        else startActivity(new Intent(Intent.ACTION_MAIN));
    }

    private void showSnackbar(int resourceId) {
        Snackbar.make(drawerLayout, resourceId, Snackbar.LENGTH_LONG).show();
    }

    private void initializeComponents() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navView = (NavigationView) findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(onNavItemSelectedListener);
        navView.setCheckedItem(R.id.drawer_parking);


        sessionSharedPreferences = getSharedPreferences(
                MyPreferences.PREFERENCE_SESSION.getKeyValue(), MODE_PRIVATE
        );
        userName = sessionSharedPreferences.getString(
                MyPreferences.SessionKeys.USERNAME_KEY.getKeyValue(),
                getResources().getString(R.string._user_name)
        );
        remember = sessionSharedPreferences.getBoolean(
                MyPreferences.SessionKeys.REMEMBER_KEY.getKeyValue(),
                false
        );

        String userMail = userName + getResources().getString(R.string._email_example);

        View view = navView.getHeaderView(0);
        // Set user name
        ((TextView)view.findViewById(R.id.txt_nav_user)).setText(userName);
        // Set user email
        ((TextView)view.findViewById(R.id.txt_nav_email)).setText(userMail);

        if (!remember) {
            showSnackbar(R.string.remeber_disabled);
        }

        mVehicleList = loadVehicleList();
        parkingFragment = ParkingFragment.newInstance(mVehicleList);
        accountPreference = new AccountPreference();
        changeFragment(parkingFragment);

        alertForRegister = makeAlertToRegister();
    }

    private void changeFragment(android.app.Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onClickFloat(CardRecyclerAdapter recyclerAdapter) {
        alertForRegister.show();
        this.recyclerAdapter = recyclerAdapter;
    }

    private AlertDialog makeAlertToRegister() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_register_parking, null);
        final TextInputLayout tilRegistration = (TextInputLayout) view.findViewById(R.id.til_alert_registration);
        final TextInputLayout tilClientId = (TextInputLayout) view.findViewById(R.id.til_alert_clientId);
        builder.setTitle(R.string.alert_parking_title);
        builder.setView(view);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showSnackbar(R.string.register_incompelte);
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mRegistrationNumber = tilRegistration.getEditText().getText().toString();
                mClientId = tilClientId.getEditText().getText().toString();
                if (mRegistrationNumber.equals("")){
                    tilRegistration.setError(getResources().getString(R.string.error_empty_field));
                    dialog.cancel();
                } else if (mClientId.equals("")) {
                    tilClientId.setError(getResources().getString(R.string.error_empty_field));
                    dialog.cancel();
                } else {
                    tilRegistration.setErrorEnabled(false);
                    tilClientId.setErrorEnabled(false);
                    mVehicleList.add(new Vehicle(mRegistrationNumber, mClientId));
                    recyclerAdapter.notifyItemInserted(mVehicleList.size());
                    saveVehicleList();
                    showSnackbar(R.string.register_complete);
                }
            }
        });
        return builder.create();
    }

    private void saveVehicleList() {
        try {
            ObjectOutputStream objOutput = new ObjectOutputStream(openFileOutput(fileName, MODE_PRIVATE));
            objOutput.writeObject(((ArrayList)mVehicleList));
            objOutput.close();
        } catch (IOException ioe) {
            Log.e(TAG, "saveVehicleList: " + ioe.getMessage(), ioe);
        }
    }

    private List<Vehicle> loadVehicleList() {
        List<Vehicle> vehicleList = new ArrayList<>();
        try {
            ObjectInputStream objInput = new ObjectInputStream(openFileInput(fileName));
            vehicleList = (ArrayList)objInput.readObject();
            objInput.close();
        } catch(IOException | ClassNotFoundException ioe) {
            Log.e(TAG, "loadVehicleList: ", ioe);
        }
        return vehicleList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.option_logout) {
            sessionSharedPreferences.edit().putBoolean(
                    MyPreferences.SessionKeys.REMEMBER_KEY.getKeyValue(),
                    false
            ).apply();
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
