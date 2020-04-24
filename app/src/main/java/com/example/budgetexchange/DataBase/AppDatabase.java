package com.example.budgetexchange.DataBase;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budgetexchange.DataBase.Comments.Comment;
import com.example.budgetexchange.DataBase.Expense.Expense;
import com.example.budgetexchange.DataBase.Expense.ExpenseDao;
import com.example.budgetexchange.DataBase.Social.Social;
import com.example.budgetexchange.DataBase.Social.SocialDao;
import com.example.budgetexchange.DataBase.Student.Student;
import com.example.budgetexchange.DataBase.Student.StudentDao;
import com.example.budgetexchange.DataBase.University.University;
import com.example.budgetexchange.DataBase.University.UniversityDao;


@Database(entities = {Student.class, Expense.class, Social.class, Comment.class, University.class} , exportSchema = false, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract StudentDao studentDao();
    public abstract UniversityDao universityDao();
    public abstract SocialDao socialDao();
    public abstract ExpenseDao expenseDao();


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "app_database")
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
        private UniversityDao universityDao;
        private SocialDao socialDao;
        private ExpenseDao expenseDao;

        private PopulateDbAsyncTask (AppDatabase db) {

            studentDao = db.studentDao();
            universityDao = db.universityDao();
            socialDao = db.socialDao();
            expenseDao = db.expenseDao();
        }

        @Override
        protected Void doInBackground (Void... voids) {
            studentDao.insert(new Student("z0000000","Corona", "Virus", "COVID-19",  "c.virus@student.unsw.edu.au", "Economics", "31/03/2020",1000f));

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
