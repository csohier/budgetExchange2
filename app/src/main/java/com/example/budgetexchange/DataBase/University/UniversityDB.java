package com.example.budgetexchange.DataBase.University;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = University.class, exportSchema = false, version = 1)
public abstract class UniversityDB extends RoomDatabase {
    private static UniversityDB instance;

    public abstract UniversityDao universityDao();

    public static synchronized UniversityDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UniversityDB.class,
                    "student_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UniversityDao universityDao;

        private PopulateDbAsyncTask(UniversityDB db) {
            universityDao = db.universityDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            universityDao.insert(new University("McGill University","Faculty of Law","Canada","North America","CAD"));
            universityDao.insert(new University("University of British Columbia","Saunder School of Business","Canada", "North America","CAD"));
            universityDao.insert(new University("Seoul National University","Built Environment/Engineering","Korea","Asia","KRW"));
            universityDao.insert(new University("Kyoto University","All available disciplines","Japan","Asia","JPY"));
            universityDao.insert(new University("University of Amsterdam","All available disciplines","Netherlands","Europe","EUR"));
            universityDao.insert(new University("University College London","Law & Built Environment","England","United Kingdom","GBP"));
            universityDao.insert(new University("University of California, Berkeley","All available disciplines","United States", "North America","USD"));
            universityDao.insert(new University("Boston College", "All available disciplines", "United States", "North America","USD"));
            universityDao.insert(new University("Chinese University of Hong Kong", "All available disciplines", "Hong Kong", "Asia","HKD"));
            universityDao.insert(new University("Copenhagen Business School", "Business School", "Denmark", "Europe","EUR"));
            universityDao.insert(new University("Hokkaido University", "Arts and Social Science/Science", "Japan", "Asia","JPY"));
            return null;
        }
    }
}
