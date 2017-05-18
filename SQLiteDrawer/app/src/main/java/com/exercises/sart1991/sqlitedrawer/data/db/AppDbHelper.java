package com.exercises.sart1991.sqlitedrawer.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exercises.sart1991.sqlitedrawer.data.db.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 5/15/2017.
 */

public class AppDbHelper implements DbHelper {

    private DbOpenHelper openHelper;

    public AppDbHelper(Context context) {
        openHelper = new DbOpenHelper(context);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        List<Vehicle> vehicleList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM vehicle", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DbInfo.Info.ID.toString()));
                String brand = cursor.getString(cursor.getColumnIndex(DbInfo.Info.BRAND.toString()));
                int quantity = cursor.getInt(cursor.getColumnIndex(DbInfo.Info.QUANTITY.toString()));
                vehicleList.add(new Vehicle(id, brand, quantity));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vehicleList;
    }

    @Override
    public void insertVehicle(Vehicle vehicle) {
        SQLiteDatabase database = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbInfo.Info.BRAND.toString(), vehicle.getBrand());
        values.put(DbInfo.Info.QUANTITY.toString(), vehicle.getQuantity());
        database.insert(DbInfo.Info.VEHICLE.toString(), DbInfo.Info.ID.toString(), values);
    }
}
