package com.exercises.sart1991.backgroundtasks.data.provider;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

/**
 * Created by sart1 on 6/3/2017.
 */

public class AppDataProvider implements DataProviderHelper {

    private ProviderContainer providerContainer =  new ProviderContainer();
    private LoaderProvider loaderProvider;
    private Context context;

    public AppDataProvider(Context context) {
        this.context = context;
        loaderProvider = new LoaderProvider(context);
    }

    @Override
    public ProviderContainer getContentProvider() {
        return providerContainer;
    }

    @Override
    public Uri insertProviderData(String data) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.getColumnName(), data);
        Uri uri = context.getContentResolver().insert(ProviderContainer.getContentUri(), values);
        return uri;
    }

    @Override
    public LoaderProvider getLoaderData(LoaderProvider.Callback callback) {
        loaderProvider.provideCallback(callback);
        return loaderProvider;
    }
}
