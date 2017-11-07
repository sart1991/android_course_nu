package com.exercise.nextu.evaluacionfinal12.ui.activities.main;

import android.content.Context;

import com.exercise.nextu.evaluacionfinal12.R;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class BarChartFormatter implements IAxisValueFormatter {

    private Context context;

    public BarChartFormatter(Context context) {
        this.context = context;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        if (value == 1) {
            return context.getString(R.string.day_monday);
        } else if (value == 2) {
            return context.getString(R.string.day_tuesday);
        } else if (value == 3) {
            return context.getString(R.string.day_wednesday);
        } else if (value == 4) {
            return context.getString(R.string.day_thursday);
        } else if (value == 5) {
            return context.getString(R.string.day_friday);
        } else if (value == 6) {
            return context.getString(R.string.day_saturday);
        } else if (value == 7) {
            return context.getString(R.string.day_sunday);
        }
        return "";
    }
}
