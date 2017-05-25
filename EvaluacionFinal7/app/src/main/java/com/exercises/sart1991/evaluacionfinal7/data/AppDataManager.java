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
    private static DataManagerListener dataManagerListener;

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
    public Donor getDonor(String donorId) {
        return dbHelper.getDonor(donorId);
    }

    @Override
    public void updateDonor(Donor donor) {
        dbHelper.updateDonor(donor);
        if (dataManagerListener != null) {
            dataManagerListener.onDonorDataChanged();
        }
    }

    @Override
    public void insertDonor(Donor donor) {
        dbHelper.insertDonor(donor);
        if (dataManagerListener != null) {
            dataManagerListener.onDonorDataChanged();
        }
    }

    @Override
    public void deleteDonor(String donorId) {
        dbHelper.deleteDonor(donorId);
        if (dataManagerListener != null) {
            dataManagerListener.onDonorDataChanged();
        }
    }

    @Override
    public boolean checkDonorExists(String id) {
        return dbHelper.checkDonorExists(id);
    }

    @Override
    public List<Donor> getAllDonors(String donorId, String userName) {
        return dbHelper.getAllDonors(donorId, userName);
    }

    @Override
    public void setDataManagerListener(DataManagerListener dataManagerListener) {
        this.dataManagerListener = dataManagerListener;
    }
}
