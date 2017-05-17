package com.exercises.sart1991.sqlitedrawer.ui.insert;

import com.exercises.sart1991.sqlitedrawer.ui.base.MvpPresenter;

/**
 * Created by sart1 on 5/16/2017.
 */

public interface InsertMvpPresenter<V extends InsertMvpView> extends MvpPresenter<V> {
    void onInsertButtonClick();
    void onShowAllButtonClick();
    void onFieldsTextChanged();
}
