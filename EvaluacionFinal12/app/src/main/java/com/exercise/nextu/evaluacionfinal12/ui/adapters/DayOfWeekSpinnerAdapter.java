package com.exercise.nextu.evaluacionfinal12.ui.adapters;


import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.exercise.nextu.evaluacionfinal12.R;
import com.exercise.nextu.evaluacionfinal12.data.models.DayOfWeek;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class DayOfWeekSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<DayOfWeek> daysOfWeek;

    public DayOfWeekSpinnerAdapter(Context context) {
        this.context = context;
        daysOfWeek = new ArrayList<>();
    }

    public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public int getCount() {
        return daysOfWeek.size();
    }

    @Override
    public DayOfWeek getItem(int i) {
        return daysOfWeek.get(i);
    }

    @Override
    public long getItemId(int i) {
        return daysOfWeek.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_days, viewGroup, false);
        }
        TextView tv = view.findViewById(R.id.textView_spinnerDays_daysOfWeek);
        tv.setText(daysOfWeek.get(i).getName());
        return view;
    }
}
