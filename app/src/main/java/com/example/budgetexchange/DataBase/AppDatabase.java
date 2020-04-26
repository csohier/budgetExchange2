package com.example.budgetexchange.DataBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.budgetexchange.DataBase.Student.Student;
import com.example.budgetexchange.DataBase.Student.StudentDao;

@Database(entities = {Student.class}, exportSchema = false, version = 10)

public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .build();
        }
        return instance;
    }
}
