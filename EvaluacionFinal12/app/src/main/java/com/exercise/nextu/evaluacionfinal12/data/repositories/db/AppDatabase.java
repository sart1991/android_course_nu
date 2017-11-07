package com.exercise.nextu.evaluacionfinal12.data.repositories.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.exercise.nextu.evaluacionfinal12.App;
import com.exercise.nextu.evaluacionfinal12.data.models.DayOfWeek;
import com.exercise.nextu.evaluacionfinal12.data.repositories.daos.DayOfWeekDao;

/**
 * Created by SergioAlejandro on 6/11/2017.
 */

@Database(entities = {DayOfWeek.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase get() {
        if (instance == null) instance = getSync();
        return instance;
    }

    private static synchronized AppDatabase getSync() {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(App.get(), AppDatabase.class, "expenses_db").build();
        }
        return instance;
    }

    public abstract DayOfWeekDao getDayOfWeekDao();

}
