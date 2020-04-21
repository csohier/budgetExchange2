package com.example.budgetexchange.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.budgetexchange.DataBase.DAOs.StudentDao;

@Database(entities = Student.class, exportSchema = false, version = 1)
public abstract class StudentDB extends RoomDatabase {
    private static final String DB_NAME = "student_db";
    private static StudentDB instance;

    public static synchronized StudentDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), StudentDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract StudentDao studentDao();
}
