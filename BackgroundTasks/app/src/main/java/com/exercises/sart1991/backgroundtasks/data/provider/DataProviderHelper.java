package com.exercises.sart1991.backgroundtasks.data.provider;

import android.net.Uri;

/**
 * Created by sart1 on 6/3/2017.
 */

public interface DataProviderHelper {
    ProviderContainer getContentProvider();
    Uri insertProviderData(String data);
    LoaderProvider getLoaderData(LoaderProvider.Callback callback);
}
