package com.exercises.sart1991.backgroundtasks.ui.activity.httpurl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.exercises.sart1991.backgroundtasks.R;
import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;
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
            getMvpView().onError(R.string.http_noConnection, null);
            Log.i(TAG, "getPeople: no connection");
            return;
        }
        Log.i(TAG, "getPeople: connection");
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                getMvpView().showProgressDialogForNetwork(R.string.dialogGet_wait);
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
                if (getMvpView().dialogForNetworkIsShowing()) {
                    getMvpView().dismissDialogForNetwork();
                    Log.i(TAG, "onPostExecute: dialog dismissed");
                }
                getMvpView().showResult(s);
                Log.i(TAG, "onPostExecute: " + s);
            }
        }.execute();
    }

    @Override
    public void getUsers() {
        if (!validateConnection()) {
            getMvpView().onError(R.string.http_noConnection, null);
            return;
        }
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                getMvpView().showProgressDialogForNetwork(R.string.dialogGet_wait);
            }

            @Override
            protected String doInBackground(Void... params) {
                return getDataManager().getUsers();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (getMvpView().dialogForNetworkIsShowing()) {
                    getMvpView().dismissDialogForNetwork();
                }
                getMvpView().showResult(s);
            }
        }.execute();
    }

    @Override
    public void addUser() {
        if (!validateConnection()) {
            getMvpView().onError(R.string.http_noConnection, null);
            return;
        }

        new AsyncTask<User, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                getMvpView().showProgressDialogForNetwork(R.string.dialogPost_wait);
                Log.i(TAG, "onPreExecute");
            }

            @Override
            protected Void doInBackground(User... params) {
                getDataManager().postUser(params[0]);
                Log.i(TAG, "doInBackground: " + params[0].toString());
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                if (getMvpView().dialogForNetworkIsShowing()) {
                    getMvpView().dismissDialogForNetwork();
                }
                getUsers();
                Log.i(TAG, "onPostExecute: " + "getUsers()");
            }
        }.execute(getJsonUser());
    }

    private boolean validateConnection() {
        ConnectivityManager manager = (ConnectivityManager) getMvpView().getViewContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private User getJsonUser() {
        return new User(1, "andres", "andres@nextu.com");
    }
}
