package com.example.budgetexchange.DataBase.Student;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budgetexchange.DataBase.Student.StudentDao;


@Database(entities = Student.class, exportSchema = false, version = 1)
public abstract class StudentDB extends RoomDatabase {
    private static StudentDB instance;

    public abstract StudentDao studentDao();

    public static synchronized StudentDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StudentDB.class,
                    "student_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentDao studentDao;
        private PopulateDbAsyncTask (StudentDB db) {
            studentDao = db.studentDao();
        }

        @Override
        protected Void doInBackground (Void... voids) {
            studentDao.insert(new Student("z0000000","Corona", "Virus", "COVID-19",  "c.virus@student.unsw.edu.au", "Economics", "London School of Economics", "31/03/2020",1000f));
            return null;
        }
    }



}
