package com.exercises.sart1991.sqlitedrawer.ui.insert;

import com.exercises.sart1991.sqlitedrawer.data.db.model.Vehicle;
import com.exercises.sart1991.sqlitedrawer.ui.base.BasePresenter;

import java.util.List;

/**
 * Created by sart1 on 5/16/2017.
 */

public class InsertPresenter<V extends InsertMvpView> extends BasePresenter<V> implements InsertMvpPresenter<V> {

    @Override
    public void onInsertButtonClick() {
        if (getMvpView().checkBrandFieldIsEmpty()) {
            getMvpView().showErrorBrandField("Error campo Marca vacio");
            getMvpView().onError("Error campo Marca vacio");
        } else if (getMvpView().checkQuantityFieldIsEmpty()) {
            getMvpView().showErrorQuantityField("Error campoCantidad vacio");
            getMvpView().onError("Error campoCantidad vacio");
        } else {
            getDataManager().insertVehicle(
                    new Vehicle(getMvpView().getBrandText(), getMvpView().getQuantityValue())
            );
        }
    }

    @Override
    public void bindTableContent() {
        List<Vehicle> vehicleList = getDataManager().getAllVehicles();
        getMvpView().populateTableContent(vehicleList);
    }

    @Override
    public void onFieldsTextChanged() {
        getMvpView().disableFieldsError();
    }
}
