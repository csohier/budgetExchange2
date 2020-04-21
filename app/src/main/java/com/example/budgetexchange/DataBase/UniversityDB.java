package com.example.budgetexchange.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.budgetexchange.DataBase.DAOs.UniversityDao;

@Database(entities = University.class, exportSchema = false, version = 1)
public abstract class UniversityDB extends RoomDatabase {
    private static final String DB_NAME = "university_db";
    private static UniversityDB instance;

    public static synchronized UniversityDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UniversityDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract UniversityDao universityDao();
}
