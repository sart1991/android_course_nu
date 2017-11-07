package com.exercise.nextu.evaluacionfinal12.ui.activities.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.exercise.nextu.evaluacionfinal12.R;
import com.exercise.nextu.evaluacionfinal12.data.DataManager;
import com.exercise.nextu.evaluacionfinal12.data.models.DayOfWeek;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<DayOfWeek>> liveDaysOfWeek;

    private BarData barData;

    private DataManager dataManager;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataManager = DataManager.get();
        liveDaysOfWeek = dataManager.getLiveDaysOfWeek();
        initBarData();
    }

    public LiveData<List<DayOfWeek>> getLiveDaysOfWeek() {
        return liveDaysOfWeek;
    }

    public void dayOfWeekChanged(DayOfWeek dayOfWeek) {
        new AsyncTask<DayOfWeek, Void, Void>() {
            @Override
            protected Void doInBackground(DayOfWeek... daysOfWeek) {
                dataManager.updateDayOfWeek(daysOfWeek[0]);
                return null;
            }
        }.execute(dayOfWeek);
    }

    public BarData getBarData() {
        return barData;
    }

    private void initBarData() {
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, 0));
        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(3f, 0));
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(5f, 0));
        entries.add(new BarEntry(6f, 0));
        entries.add(new BarEntry(7f, 0));
        BarDataSet dataSet1 = new BarDataSet(entries, "Gastos Semanales");
        dataSet1.setColor(getApplication().getResources().getColor(R.color.colorAccent));
        barData = new BarData(dataSet1);
    }

}
