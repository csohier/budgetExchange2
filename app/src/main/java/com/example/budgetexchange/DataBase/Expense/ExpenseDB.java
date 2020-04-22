package com.example.budgetexchange.DataBase.Expense;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Expense.class, exportSchema = false, version = 1)
public abstract class ExpenseDB extends RoomDatabase {
    private static ExpenseDB instance;

    public abstract ExpenseDao expenseDao();

    public static synchronized ExpenseDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ExpenseDB.class, "expense_db")
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
        private ExpenseDao expenseDao;
        private PopulateDbAsyncTask (ExpenseDB db) {
            expenseDao = db.expenseDao();


        }

        @Override
        protected Void doInBackground(Void... voids) {
            expenseDao.insert(new Expense(201.00, "Food", "04/04/2020"));
            expenseDao.insert(new Expense(75.50, "Miscellaneous", "11/04/2020"));
            expenseDao.insert(new Expense(50.50, "Personal", "18/04/2020"));
            expenseDao.insert(new Expense(41.00, "Food", "19/04/2020"));
            expenseDao.insert(new Expense(75.50, "Miscellaneous", "19/04/2020"));
            expenseDao.insert(new Expense(55.50, "Personal", "19/04/2020"));
            expenseDao.insert(new Expense(221.00, "Food", "19/04/2020"));
            expenseDao.insert(new Expense(20.50, "Miscellaneous", "19/04/2020"));
            expenseDao.insert(new Expense(5.50, "Personal", "19/04/2020"));

            return null;
        }
    }


}
