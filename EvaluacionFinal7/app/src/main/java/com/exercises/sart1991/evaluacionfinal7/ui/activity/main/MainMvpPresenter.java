package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import android.view.MotionEvent;
import android.view.View;

import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/19/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void validateSession();
    void onWelcome(View view);
    void onOptionItemClick(int itemId);
    void onClickAddDonor();
    void onDialogDonorIdTyping(String id, String idExcept);
    void onCancelNewDonor();
    void onFirstLoadDonorData();
    void onDonorMenuClick(Donor donor, int itemId);
    void onDialogDonorPositive(
            String id, String name, String lastName, String age, String bloodType,
            String rh, String weight, String height, String idExcept
    );
    void onCancelEditDonor();
    void onConfirmDeleteDonor(String id);
    void onCancelDeleteDonor();
    void onDonorIdFilterTyping(String id);
    void onTouchClearSearch();
    void onCancelNewPassword();
    void onChangeNewPassword(String currentPassword, String newPassword, String confPassword);
    void onTypingCurrentPassword(String currentPassword);
    void onTypingConfPassword(String newPassword, String confPassword);
    void onCancelDeleteAccount();
    void onDeleteAccount();
}
