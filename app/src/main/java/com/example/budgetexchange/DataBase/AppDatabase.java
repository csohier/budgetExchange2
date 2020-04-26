package com.example.budgetexchange.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.budgetexchange.DataBase.Achievements.AcheivementsDao;
import com.example.budgetexchange.DataBase.Achievements.Achievements;
import com.example.budgetexchange.DataBase.Comments.Comment;
import com.example.budgetexchange.DataBase.Comments.CommentDao;
import com.example.budgetexchange.DataBase.Expense.Expense;
import com.example.budgetexchange.DataBase.Expense.ExpenseDao;
import com.example.budgetexchange.DataBase.Goals.Goals;
import com.example.budgetexchange.DataBase.Goals.GoalsDao;
import com.example.budgetexchange.DataBase.Social.Social;
import com.example.budgetexchange.DataBase.Social.SocialDao;
import com.example.budgetexchange.DataBase.Student.Student;
import com.example.budgetexchange.DataBase.Student.StudentDao;
import com.example.budgetexchange.DataBase.University.University;
import com.example.budgetexchange.DataBase.University.UniversityDao;


@Database(entities = {Student.class, Expense.class, Social.class, Comment.class,
                      University.class, Goals.class, Achievements.class},
                      exportSchema = false, version = 8)

public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao studentDao();
    public abstract UniversityDao universityDao();
    public abstract SocialDao socialDao();
    public abstract ExpenseDao expenseDao();
    public abstract CommentDao commentDao();
    public abstract AcheivementsDao achievementsDao();
    public abstract GoalsDao goalsDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                //.addCallback(roomCallback)
                //.allowMainThreadQueries()
                .build();
        }
        return instance;
    }

    /*
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
        private CommentDao commentDao;
        private AcheivementsDao acheivementsDao;
        private GoalsDao goalsDao;

        private PopulateDbAsyncTask (AppDatabase db) {

            studentDao = db.studentDao();
            universityDao = db.universityDao();
            socialDao = db.socialDao();
            expenseDao = db.expenseDao();
            commentDao = db.commentDao();
            acheivementsDao = db.achievementsDao();
            goalsDao = db.goalsDao();
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

            acheivementsDao.insert(new Achievements("Novice", 10));
            acheivementsDao.insert(new Achievements("Beginner", 25));
            acheivementsDao.insert(new Achievements("Intermediate",50));
            acheivementsDao.insert(new Achievements("Advanced", 80));
            acheivementsDao.insert(new Achievements("Superior", 100));

            goalsDao.insert(new Goals("z0000000", 350, 8000, "01/04/2020", "14/08/2020"));
            goalsDao.insert(new Goals("z5431234", 500, 7000, "22/09/2019", "22/12/2019"));

            return null;
        }
    }*/
}
