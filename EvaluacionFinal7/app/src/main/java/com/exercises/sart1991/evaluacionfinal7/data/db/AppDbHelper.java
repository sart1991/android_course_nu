package com.exercises.sart1991.evaluacionfinal7.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 5/22/2017.
 */

public class AppDbHelper implements DbHelper {

    private static final String TAG = AppDbHelper.class.getSimpleName();
    private DbOpenHelper dbOpenHelper;

    public AppDbHelper(Context context) {
        dbOpenHelper = new DbOpenHelper(context);
    }

    @Override
    public User getUser(String userName) {
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                sentenceSelect(DbInfo.TablesInfo.USER_TABLE.getTableName(), "user_name"),
                new String[]{userName}
        );
        try {
            return readCursorUser(cursor).get(0);
        } catch (IndexOutOfBoundsException iooe) {
            Log.e(TAG, "getUser: ", iooe);
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        database.update(
                DbInfo.TablesInfo.USER_TABLE.getTableName(),
                getUserValues(user),
                "user_name = ?",
                new String[]{user.getUserName()}
        );
    }

    @Override
    public void insertUser(User user) {
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        database.insert(DbInfo.TablesInfo.USER_TABLE.getTableName(), null, getUserValues(user));
    }

    @Override
    public void deleteUser(String userName) {
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        database.delete(
                DbInfo.TablesInfo.USER_TABLE.getTableName(),
                "user_name = ?", new String[]{userName});
    }

    @Override
    public List<User> getAllUsers() {
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT * FROM " + DbInfo.TablesInfo.USER_TABLE.getTableName(),
                null
        );
        return readCursorUser(cursor);
    }

    @Override
    public Donor getDonor(int donorId) {
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                sentenceSelect(
                        DbInfo.TablesInfo.DONOR_TABLE.getTableName(),
                        DbInfo.DonorInfo.ID.toString()
                        ),
                new String[] {String.valueOf(donorId)}
        );
        return readCursorDonor(cursor).get(0);
    }

    @Override
    public void updateDonor(Donor donor) {
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        database.update(
                DbInfo.TablesInfo.DONOR_TABLE.getTableName(),
                getDonorValues(donor),
                "id = ?",
                new String[] {String.valueOf(donor.getId())}
        );
    }

    @Override
    public void insertDonor(Donor donor) {
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        database.insert(
                DbInfo.TablesInfo.DONOR_TABLE.getTableName(),
                null,
                getDonorValues(donor)
        );
    }

    @Override
    public void deleteDonor(int donorId) {
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        database.delete(
                DbInfo.TablesInfo.DONOR_TABLE.getTableName(),
                "id = ?",
                new String[] {String.valueOf(donorId)}
        );
    }

    @Override
    public List<Donor> getAllDonors() {
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT * FROM " + DbInfo.TablesInfo.DONOR_TABLE.getTableName(),
                null
        );
        return readCursorDonor(cursor);
    }

    @Override
    public List<Donor> getAllDonorsFromUser(String userName) {
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT * FROM " +
                        DbInfo.TablesInfo.DONOR_TABLE.getTableName() +
                " WHERE user_name=?",
                new String[] {userName}
        );
        return readCursorDonor(cursor);
    }

    private String sentenceSelect(String tableName, String primaryKey) {
        return "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ?;";
    }

    private ContentValues getUserValues(User user) {
        ContentValues values = new ContentValues();
        values.put(DbInfo.UserInfo.USER_NAME.toString(), user.getUserName());
        values.put(DbInfo.UserInfo.PASSWORD.toString(), user.getPassword());
        return values;
    }

    private ContentValues getDonorValues(Donor donor) {
        ContentValues values = new ContentValues();
        values.put(DbInfo.DonorInfo.ID.toString(), donor.getId());
        values.put(DbInfo.DonorInfo.NAME.toString(), donor.getName());
        values.put(DbInfo.DonorInfo.LAST_NAME.toString(), donor.getLastName());
        values.put(DbInfo.DonorInfo.ID.toString(), donor.getAge());
        values.put(DbInfo.DonorInfo.ID.toString(), donor.getBloodType());
        values.put(DbInfo.DonorInfo.ID.toString(), donor.getRh());
        values.put(DbInfo.DonorInfo.ID.toString(), donor.getWeight());
        values.put(DbInfo.DonorInfo.ID.toString(), donor.getHeight());
        values.put(DbInfo.DonorInfo.USER_NAME.toString(), donor.getForUserName());
        return values;
    }

    private List<User> readCursorUser(Cursor c) {
        List<User> usersList = new ArrayList<>(1);
        if (c.moveToFirst()) {
            do {
                String userName = c.getString(c.getColumnIndex(""+DbInfo.UserInfo.USER_NAME));
                String password = c.getString(c.getColumnIndex(""+DbInfo.UserInfo.PASSWORD));
                usersList.add(new User(userName, password));
            } while (c.moveToNext());
        }
        c.close();
        return usersList;
    }

    private List<Donor> readCursorDonor(Cursor c) {
        List<Donor> usersList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(""+DbInfo.DonorInfo.ID.toString()));
                String name = c.getString(c.getColumnIndex(""+DbInfo.DonorInfo.NAME));
                String lastName = c.getString(c.getColumnIndex(""+DbInfo.DonorInfo.LAST_NAME));
                int age = c.getInt(c.getColumnIndex(""+DbInfo.DonorInfo.AGE.toString()));
                String bloodType = c.getString(c.getColumnIndex(""+DbInfo.DonorInfo.BLOOD_TYPE));
                String rh = c.getString(c.getColumnIndex(""+DbInfo.DonorInfo.RH.toString()));
                int weight = c.getInt(c.getColumnIndex(""+DbInfo.DonorInfo.WEIGHT));
                int height = c.getInt(c.getColumnIndex(""+DbInfo.DonorInfo.HEIGHT));
                String forUserName = c.getString(c.getColumnIndex(""+DbInfo.DonorInfo.USER_NAME));
                usersList.add(
                    new Donor(
                        id, name, lastName, age, bloodType, rh, weight, height, forUserName
                    )
                );
            } while (c.moveToNext());
        }
        c.close();
        return usersList;
    }


}
