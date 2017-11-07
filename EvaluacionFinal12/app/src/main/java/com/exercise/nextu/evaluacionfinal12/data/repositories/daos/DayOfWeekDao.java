package com.exercise.nextu.evaluacionfinal12.data.repositories.daos;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.exercise.nextu.evaluacionfinal12.data.models.DayOfWeek;
import com.exercise.nextu.evaluacionfinal12.utils.DaysOfWeekUtil;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */
@Dao
public interface DayOfWeekDao {

    @Insert(onConflict = REPLACE)
    void addDayOfWeek(DayOfWeek daysOfWeek);

    @Update
    void updateDayOfWeek(DayOfWeek dayOfWeek);

    @Delete
    void deleteDayOfWeek(DayOfWeek dayOfWeek);

    @Query("SELECT * FROM days_of_week")
    LiveData<List<DayOfWeek>> getAllDaysOfWeek();

}
