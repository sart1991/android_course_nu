package com.exercises.sart1991.evaluacionfinal7.data.db;

import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.User;

import java.util.List;

/**
 * Created by sart1 on 5/22/2017.
 */

public interface DbHelper {
    User getUser(String userName);
    void updateUser(User user);
    void insertUser(User user);
    void deleteUser(String userName);
    List<User> getAllUsers();

    Donor getDonor(String donorId);
    void updateDonor(Donor donor);
    void insertDonor(Donor donor);
    void deleteDonor(String donorId);
    boolean checkDonorExists(String id);
    List<Donor> getAllDonors();
    List<Donor> getAllDonors(long donorId);
    List<Donor> getAllDonors(String userName);
}
