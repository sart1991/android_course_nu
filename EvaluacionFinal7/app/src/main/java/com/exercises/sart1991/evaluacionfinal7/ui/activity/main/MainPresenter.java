package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
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
        Log.i(TAG, "validateSession: outer");
        if(!isSessionActive()) {
            Log.i(TAG, "validateSession: inner");
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
        getMvpView().setToolbarSubtitle(getCheckedUserName());
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

    private void onRegisterNewDonor(Donor donor) {

        if (donor != null) {
            getDataManager().insertDonor(donor);
            getMvpView().onSuccess(R.string.main_successDonorRegistered, view);
            getMvpView().cleanDialogNewDonorData();
        }
    }

    @Override
    public void onFirstLoadDonorData() {
        if (isSessionActive()) {
            getMvpView().initializeCards();
            getDataManager().setDataManagerListener(dataManagerListener);
            getMvpView().setDonorList(getDataManager().getAllDonors(null, getCheckedUserName()));
        }
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

    @Override
    public void onCancelEditDonor() {
        getMvpView().cleanDialogNewDonorData();
        getMvpView().onNotify(R.string.main_notifyEditDonorCanceled, view);
    }

    private void onConfirmEditDonor(Donor donor) {
        getMvpView().cleanDialogNewDonorData();
        if (donor != null) {
            getDataManager().updateDonor(donor);
            getMvpView().onSuccess(R.string.main_successDonorUpdated, view);
        }
    }

    private void onDeleteDonorClick(Donor donor) {
        getMvpView().showDialogDeleteDonor(donor);
    }

    @Override
    public void onCancelDeleteDonor() {
        getMvpView().onNotify(R.string.main_notifyDeleteDonorCanceled, view);
    }

    @Override
    public void onConfirmDeleteDonor(String id) {
        getDataManager().deleteDonor(id);
        getMvpView().onNotify(R.string.main_notifyDonorDeleted, view);
    }

    @Override
    public void onDialogDonorPositive(String id, String name, String lastName,
                                      String age, String bloodType, String rh,
                                      String weight, String height, String idExcept) {
        Donor d = validateDonor(id, name, lastName, age, bloodType, rh, weight, height, idExcept);
        if (idExcept == null) {
            onRegisterNewDonor(d);
        } else {
            onConfirmEditDonor(d);
        }
    }

    private @Nullable Donor validateDonor(String id, String name, String lastName,
                                          String age, String bloodType, String rh,
                                          String weight, String height, String idExcept) {
        if (!validateFieldsNotEmpty(id, name, lastName, age, bloodType, rh, weight, height)) {
            return null;
        }

        if (!validateDonorId(id, idExcept, true)) {
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

    private boolean validateFieldsNotEmpty(String id, String name, String lastName,
                                           String age, String bloodType, String rh,
                                           String weight, String height) {

        if ("".equals(id) || "".equals(name) || "".equals(lastName) ||
                "".equals(age) || "".equals(bloodType) || "".equals(rh) ||
                "".equals(weight) || "".equals(height)) {
            getMvpView().onError(R.string.main_errorEmptyFieldsFound, view);
            return false;
        }
        return true;
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

    @Override
    public void onDonorIdFilterTyping(String id) {
        getMvpView().setDonorList(getDataManager().getAllDonors(id, getCheckedUserName()));
        getMvpView().onDonorListChanged();
    }

    @Override
    public void onTouchClearSearch() {
        getMvpView().clearSearch();
    }

    private String getCheckedUserName() {
        if (getMvpView().checkFilterUserState()) {
            Log.i(TAG, "getCheckedUserName: donorCheck");
            return getDataManager().getUserName();
        }
        return "";
    }

    private DataManager.DataManagerListener dataManagerListener = new DataManager.DataManagerListener() {
        @Override
        public void onDonorDataChanged() {
            getMvpView().setDonorList(getDataManager().getAllDonors(null, getCheckedUserName()));
            getMvpView().onDonorListChanged();
        }
    };
}
