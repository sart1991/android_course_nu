package com.exercises.sart1991.backgroundtasks.ui.activity.httpurl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.exercises.sart1991.backgroundtasks.ui.base.BasePresenter;

/**
 * Created by sart1 on 5/30/2017.
 */

public class HttpPresenter<V extends HttpMvpView>
        extends BasePresenter<V> implements HttpMvpPresenter<V> {

    private static final String TAG = HttpPresenter.class.getSimpleName();

    @Override
    public void getPeople() {
        if (!validateConnection()) {
            getMvpView().onError("No esta conectado", null);
            Log.i(TAG, "getPeople: no connection");
            return;
        }
        Log.i(TAG, "getPeople: connection");
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                getMvpView().showProgressDialogForGetPeople();
                Log.i(TAG, "onPreExecute");
            }

            @Override
            protected String doInBackground(Void... params) {
                Log.i(TAG, "doInBackground: " + getDataManager().getPeople());
                return getDataManager().getPeople();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (getMvpView().dialogForGetPeopleIsShowing()) {
                    getMvpView().dismissDialogForGetPeople();
                    Log.i(TAG, "onPostExecute: dialog dismissed");
                }
                getMvpView().showResult(s);
                Log.i(TAG, "onPostExecute: " + s);
            }
        }.execute();
    }

    private boolean validateConnection() {
        ConnectivityManager manager = (ConnectivityManager) getMvpView().getViewContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
