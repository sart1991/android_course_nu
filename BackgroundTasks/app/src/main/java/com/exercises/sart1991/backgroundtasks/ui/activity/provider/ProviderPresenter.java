package com.exercises.sart1991.backgroundtasks.ui.activity.provider;

import android.net.Uri;
import android.app.LoaderManager;
import android.util.Log;

import com.exercises.sart1991.backgroundtasks.data.provider.LoaderProvider;
import com.exercises.sart1991.backgroundtasks.data.provider.ProviderContainer;
import com.exercises.sart1991.backgroundtasks.ui.base.BasePresenter;

/**
 * Created by sart1 on 6/3/2017.
 */

public class ProviderPresenter<V extends ProviderMvpView>
        extends BasePresenter<V> implements ProviderMvpPresenter<V> {

    private static final String TAG = ProviderPresenter.class.getSimpleName();

    @Override
    public void generateProvider() {
        ProviderContainer providerContainer = getDataManager().getContentProvider();
        if (providerContainer != null) {
            getMvpView().onSuccess("Provider created", null);
        } else {
            getMvpView().onError("Provider not created", null);
        }
    }

    @Override
    public void insertData() {
        Uri uri = getDataManager().insertProviderData("Yo");
        getMvpView().onNotify(uri.toString(), null);
    }

    @Override
    public void getProviderData(LoaderManager loaderManager) {
        LoaderProvider lProvider = getDataManager().getLoaderData(new LoaderProvider.Callback() {
            @Override
            public void onLoaderFinished(String result) {
                getMvpView().setResult(result);
            }

            @Override
            public void onLoaderError() {
                getMvpView().onError("Error en la carga de datos", null);
            }
        });
        loaderManager.initLoader(1, null, lProvider);
    }
}
