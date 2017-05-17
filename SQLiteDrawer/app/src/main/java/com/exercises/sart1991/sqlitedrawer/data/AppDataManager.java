package com.exercises.sart1991.sqlitedrawer.data;

import android.content.Context;

import com.exercises.sart1991.sqlitedrawer.data.db.AppDbHelper;
import com.exercises.sart1991.sqlitedrawer.data.db.DbHelper;
import com.exercises.sart1991.sqlitedrawer.data.db.model.Vehicle;

import java.util.List;

/**
 * Created by sart1 on 5/15/2017.
 */

public class AppDataManager implements DataManager{

    private DbHelper mDbHelper;

    public AppDataManager(Context context) {
        mDbHelper = new AppDbHelper(context);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return mDbHelper.getAllVehicles();
    }

    @Override
    public void insertVehicle(Vehicle vehicle) {
        mDbHelper.insertVehicle(vehicle);
    }
}
