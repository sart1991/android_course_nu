package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpView;

import java.util.List;

/**
 * Created by sart1 on 5/19/2017.
 */

interface MainMvpView extends MvpView {

    void gotoLogin();
    void setToolbarSubtitle(String subtitle);
    void showDialogNewDonor();
    void cleanDialogNewDonorData();
    void onDialogDonorIdError(int errorResId);
    void initializeCards();
    void setDonorList(List<Donor> donorList);
    void onDonorListChanged();
    void showDialogEditDonor(Donor donor);
    void showDialogDeleteDonor(Donor donor);
    void clearSearch();
    boolean checkFilterUserState();
}
