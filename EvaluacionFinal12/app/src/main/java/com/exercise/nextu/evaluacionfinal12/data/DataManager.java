package com.exercise.nextu.evaluacionfinal12.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.exercise.nextu.evaluacionfinal12.App;
import com.exercise.nextu.evaluacionfinal12.data.models.DayOfWeek;
import com.exercise.nextu.evaluacionfinal12.data.repositories.db.AppDatabase;
import com.exercise.nextu.evaluacionfinal12.data.repositories.preferences.PreferencesRepository;
import com.exercise.nextu.evaluacionfinal12.utils.DaysOfWeekUtil;

import java.util.List;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private static DataManager instance;
    private AppDatabase appDatabase;
    private PreferencesRepository preferencesRepository;

    private DataManager() {}

    public static DataManager get() {
        if (instance == null) instance = getSync();
        return instance;
    }

    private static synchronized DataManager getSync() {
        if (instance == null) instance = new DataManager();
        instance.appDatabase = AppDatabase.get();
        instance.preferencesRepository = PreferencesRepository.get();
        return instance;
    }

    public LiveData<List<DayOfWeek>> getLiveDaysOfWeek() {
        if (preferencesRepository.isFirstRun()) {
            runFirstDaysLoadAsync();
        }
        return appDatabase.getDayOfWeekDao().getAllDaysOfWeek();
    }

    public void updateDayOfWeek(DayOfWeek dayOfWeek) {
        appDatabase.getDayOfWeekDao().updateDayOfWeek(dayOfWeek);
    }

    private void runFirstDaysLoadAsync() {
        new AsyncTask<List<DayOfWeek>, Void, Void>() {
            @Override
            protected Void doInBackground(List<DayOfWeek>[] lists) {
                runFirstDaysLoad(lists[0]);
                return null;
            }
        }.execute(DaysOfWeekUtil.getFirstDaysOfWeek(App.get()));
    }

    private void runFirstDaysLoad(List<DayOfWeek> daysOfWeek) {
        if (preferencesRepository.isFirstRun()) {
            Log.i(TAG, "runFirstDaysLoad true");
            preferencesRepository.setFirstRun(false);
            for (DayOfWeek d : daysOfWeek) {
                appDatabase.getDayOfWeekDao().addDayOfWeek(d);
            }
        } else {
            Log.i(TAG, "runFirstDaysLoad false");

        }
    }
}
