package com.exercise.nextu.evaluacionfinal12.utils;

import android.content.Context;

import com.exercise.nextu.evaluacionfinal12.R;
import com.exercise.nextu.evaluacionfinal12.data.models.DayOfWeek;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

public class DaysOfWeekUtil {

    private DaysOfWeekUtil() {}

    public static List<DayOfWeek> getFirstDaysOfWeek(Context c) {
        List<DayOfWeek> dayOfWeekList = new ArrayList<>();
        dayOfWeekList.add(getMonday(c));
        dayOfWeekList.add(getTuesday(c));
        dayOfWeekList.add(getWednesday(c));
        dayOfWeekList.add(getThursday(c));
        dayOfWeekList.add(getFriday(c));
        dayOfWeekList.add(getSaturday(c));
        dayOfWeekList.add(getSunday(c));
        return  dayOfWeekList;
    }

    private static DayOfWeek getMonday(Context c) {
        return new DayOfWeek(0, c.getString(R.string.day_monday), 0);
    }

    private static DayOfWeek getTuesday(Context c) {
        return new DayOfWeek(1, c.getString(R.string.day_tuesday), 0);
    }

    private static DayOfWeek getWednesday(Context c) {
        return new DayOfWeek(2, c.getString(R.string.day_wednesday), 0);
    }

    private static DayOfWeek getThursday(Context c) {
        return new DayOfWeek(3, c.getString(R.string.day_thursday), 0);
    }

    private static DayOfWeek getFriday(Context c) {
        return new DayOfWeek(4, c.getString(R.string.day_friday), 0);
    }

    private static DayOfWeek getSaturday(Context c) {
        return new DayOfWeek(5, c.getString(R.string.day_saturday), 0);
    }

    private static DayOfWeek getSunday(Context c) {
        return new DayOfWeek(6, c.getString(R.string.day_sunday), 0);
    }

}
