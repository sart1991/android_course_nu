package com.exercises.sart1991.sqlitedrawer.data.db;

import com.exercises.sart1991.sqlitedrawer.data.db.model.Vehicle;

import java.util.List;

/**
 * Created by sart1 on 5/15/2017.
 */

public interface DbHelper {
    List<Vehicle> getAllVehicles();
    void insertVehicle(Vehicle vehicle);
}
