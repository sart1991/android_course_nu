package com.exercises.sart1991.backgroundtasks.data.provider;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by sart1 on 6/3/2017.
 */

public class LoaderProvider implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = LoaderProvider.class.getSimpleName();

    private CursorLoader cursorLoader;
    private Context context;
    private Callback callback;

    LoaderProvider(Context context) {
        this.context = context;
    }

    void provideCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader = new CursorLoader(
                context, ProviderContainer.getContentUri(), null, null, null, null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {
            StringBuilder sb = new StringBuilder();
            do {
                sb.append("\n")
                        .append(data.getInt(data.getColumnIndex(DbHelper.getColumnId())))
                        .append(" - ")
                        .append(data.getString(data.getColumnIndex(DbHelper.getColumnName())));
                if (callback != null) {
                    callback.onLoaderFinished(sb.toString());
                } else {
                    Log.i(TAG, "onLoadFinished: no error");
                }
            } while (data.moveToNext());
        } else {
            if (callback != null) {
                callback.onLoaderError();
            } else {
                Log.i(TAG, "onLoadFinished: error");
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public interface Callback {
        void onLoaderFinished(String result);

        void onLoaderError();
    }
}
