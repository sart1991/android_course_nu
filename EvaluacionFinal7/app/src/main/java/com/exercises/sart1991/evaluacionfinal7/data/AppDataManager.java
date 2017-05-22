package com.exercises.sart1991.evaluacionfinal7.data;

import android.content.Context;

import com.exercises.sart1991.evaluacionfinal7.data.db.AppDbHelper;
import com.exercises.sart1991.evaluacionfinal7.data.db.DbHelper;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.User;
import com.exercises.sart1991.evaluacionfinal7.data.preferences.AppPreferencesHelper;
import com.exercises.sart1991.evaluacionfinal7.data.preferences.PreferencesHelper;

import java.util.List;

/**
 * Created by sart1 on 5/18/2017.
 */

public class AppDataManager implements DataManager {

    private DbHelper dbHelper;
    private PreferencesHelper prefHelper;

    public AppDataManager(Context context) {
        dbHelper = new AppDbHelper(context);
        prefHelper = new AppPreferencesHelper(context);
    }

    @Override
    public boolean getLoginState() {
        return prefHelper.getLoginState();
    }

    @Override
    public void setLoginState(boolean isLogged) {
        prefHelper.setLoginState(isLogged);
    }

    @Override
    public String getUserName() {
        return prefHelper.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        prefHelper.setUserName(userName);
    }

    @Override
    public User getUser(String userName) {
        return dbHelper.getUser(userName);
    }

    @Override
    public void updateUser(User user) {
        dbHelper.updateUser(user);
    }

    @Override
    public void insertUser(User user) {
        dbHelper.insertUser(user);
    }

    @Override
    public void deleteUser(String userName) {
        dbHelper.deleteUser(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return dbHelper.getAllUsers();
    }

    @Override
    public Donor getDonor(int donorId) {
        return dbHelper.getDonor(donorId);
    }

    @Override
    public void updateDonor(Donor donor) {
        dbHelper.updateDonor(donor);
    }

    @Override
    public void insertDonor(Donor donor) {
        dbHelper.insertDonor(donor);
    }

    @Override
    public void deleteDonor(int donorId) {
        dbHelper.deleteDonor(donorId);
    }

    @Override
    public List<Donor> getAllDonors() {
        return dbHelper.getAllDonors();
    }

    @Override
    public List<Donor> getAllDonorsFromUser(String userName) {
        return dbHelper.getAllDonorsFromUser(userName);
    }
}
