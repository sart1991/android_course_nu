package com.exercises.sart1991.evaluacionfinal8p.data.provider;
import android.net.Uri;

import com.exercises.sart1991.evaluacionfinal8p.data.apischool.model.Task;

import java.util.List;

/**
 * Created by sart1 on 6/3/2017.
 */

public interface DataProviderHelper {
    ProviderContainer getContentProvider();
    Uri insertProviderTask(List<Task> tasks);
    ProviderLoader getLoaderData(ProviderLoader.Callback callback);
}
