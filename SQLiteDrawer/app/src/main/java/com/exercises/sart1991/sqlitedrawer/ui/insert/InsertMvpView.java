package com.exercises.sart1991.sqlitedrawer.ui.insert;

import com.exercises.sart1991.sqlitedrawer.ui.base.MvpView;

/**
 * Created by sart1 on 5/16/2017.
 */

public interface InsertMvpView extends MvpView {

    String getBrandText();
    int getQuantityValue();
    boolean checkBrandFieldIsEmpty();
    boolean checkQuantityFieldIsEmpty();
    void showErrorBrandField(String error);
    void showErrorQuantityField(String error);
    void disableFieldsError();
    void addTableRow(String content);
    void clearTableContentData();
}
