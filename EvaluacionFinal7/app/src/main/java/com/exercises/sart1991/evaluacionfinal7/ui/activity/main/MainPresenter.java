package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.data.DataManager;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BasePresenter;

/**
 * Created by sart1 on 5/19/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private View view;

    @Override
    public void validateSession() {
        if(!isSessionActive()) {
            getMvpView().gotoLogin();
        }
    }

    @Override
    public void onWelcome(View view) {
        String message =
                getMvpView().getViewContext().getString(R.string.main_notifyWelcome) +
                " " + getDataManager().getUserName();
        getMvpView().onNotify(message, view);
        this.view = view;
    }

    @Override
    public void onOptionItemClick(int itemId) {
        switch (itemId) {
            case R.id.item_mainOptions_logout:
                getDataManager().setLoginState(false);
                getMvpView().gotoLogin();
                break;
        }
    }

    @Override
    public void onClickAddDonor() {
        getMvpView().showDialogNewDonor();
    }

    @Override
    public void onDialogDonorIdTyping(String id, String idExcept) {
        if (!"".equals(id)){
            validateDonorId(id, idExcept, false);
        }
    }

    @Override
    public void onCancelNewDonor() {
        getMvpView().onNotify(R.string.main_notifyDonorCanceled, view);
    }

    @Override
    public void onDialogNewDonorRegister(String id, String name, String lastName,
                                         String age, String bloodType, String rh,
                                         String weight, String height) {

        Donor donor = validateNewUser(id, name, lastName, age, bloodType, rh, weight, height);
        if (donor != null) {
            getDataManager().insertDonor(donor);
            getMvpView().onSuccess(R.string.main_successDonorRegistered, view);
            getMvpView().cleanDialogNewDonorData();
        }

    }

    @Override
    public void onFirstLoadDonorData() {
        getMvpView().initializeCards();
        getDataManager().setDataManagerListener(dataManagerListener);
        getMvpView().setDonorList(getDataManager().getAllDonors(getDataManager().getUserName()));
    }



    @Override
    public void onDonorMenuClick(Donor donor, int itemId) {
        switch (itemId) {
            case R.id.item_carViewDonor_edit:
                onEditDonorClick(donor);
                break;
            case R.id.item_cardViewDonor_delete:
                onDeleteDonorClick(donor);
                break;
        }
    }

    private void onEditDonorClick(Donor donor) {
        getMvpView().showDialogEditDonor(donor);
    }

    private void onDeleteDonorClick(Donor donor) {
        getMvpView().showDialogDeleteDonor(donor);
    }

    @Override
    public void onConfirmEditDonor(
            String id, String name, String lastName,
            String age, String bloodType, String rh,
            String weight, String height) {
        getMvpView().cleanDialogNewDonorData();
    }

    @Override
    public void onCancelEditDonor() {
        getMvpView().cleanDialogNewDonorData();
    }

    @Override
    public void onConfirmDeleteDonor(String id) {

    }

    @Override
    public void onCancelDeleteDonor() {

    }

    private @Nullable Donor validateNewUser(String id, String name, String lastName,
                                            String age, String bloodType, String rh,
                                            String weight, String height) {
        if ("".equals(id) || "".equals(name) || "".equals(lastName) ||
            "".equals(age) || "".equals(bloodType) || "".equals(rh) ||
            "".equals(weight) || "".equals(height)) {
            getMvpView().onError(R.string.main_errorEmptyFieldsFound, view);
            return null;
        }

        if (!validateDonorId(id, null, true)) {
            return null;
        }
        if(!validateDonorSpinnersContent(bloodType, rh)) {
            return null;
        }
        return new Donor(
                id,
                name, lastName,
                Integer.valueOf(age),
                bloodType, rh,
                Integer.valueOf(weight),
                Integer.valueOf(height),
                getDataManager().getUserName()
        );
    }

    private boolean validateDonorId(String id, String idExcept, boolean showSnack) {
        int donorId = Integer.valueOf(id);
        if (donorId == 0) {
            if (showSnack) {
                getMvpView().onError(R.string.main_errorIdValueNotValid, view);
            }
            getMvpView().onDialogDonorIdError(R.string.main_errorIdValueNotValid);
            return false;
        }
        Log.i(TAG, "validateDonorId: " + id + " idExcept: " + idExcept);
        Log.i(TAG, "validateDonorId: exists: " + getDataManager().checkDonorExists(id));
        Log.i(TAG, "validateDonorId: equals: " + !id.equals(idExcept));
        if (getDataManager().checkDonorExists(id) && !id.equals(idExcept)) {
            Log.i(TAG, "validateDonorId: " + id);
            if (showSnack) {
                getMvpView().onError(R.string.main_errorDonorAlreadyExists, view);
            }
            getMvpView().onDialogDonorIdError(R.string.main_errorDonorAlreadyExists);
            return false;
        }
        return true;
    }

    private boolean validateDonorSpinnersContent(String bloodType, String rh) {
        return validateBloodTypeSelection(bloodType) && validateRhSelection(rh);
    }

    private boolean validateBloodTypeSelection(String bloodType) {
        if (getMvpView().getViewContext().getString(R.string._blood_type).equals(bloodType)) {
            getMvpView().onError(R.string.main_errorBloodTypeNotSelected, view);
            return false;
        }
        return true;
    }

    private boolean validateRhSelection(String rh) {
        if (getMvpView().getViewContext().getString(R.string._rh).equals(rh)) {
            getMvpView().onError(R.string.main_errorRhNotSelected, view);
            return false;
        }
        return true;
    }

    private DataManager.DataManagerListener dataManagerListener = new DataManager.DataManagerListener() {
        @Override
        public void onDonorDataChanged() {
            getMvpView().setDonorList(getDataManager().getAllDonors(getDataManager().getUserName()));
            getMvpView().onDonorListChanged();
        }
    };
}
