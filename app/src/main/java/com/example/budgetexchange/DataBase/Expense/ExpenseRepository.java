package com.example.budgetexchange.DataBase.Expense;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseRepository {
    private ExpenseDao expenseDao;
    private LiveData<List<Expense>> allExpense;

    public ExpenseRepository (Application application) {
        ExpenseDB db = ExpenseDB.getInstance(application);
        expenseDao = db.expenseDao();
    }

    public void insert (Expense expense) {

    }
    public void update (Expense expense) {

    }
    public void delete (Expense expense) {

    }
    public void deleteAllExpense() {

    }

    public LiveData<List<Expense>> getAllExpense() {
        return allExpense;
    }

    private static class InsertExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {
        private ExpenseDao expenseDao;
        private InsertExpenseAsyncTask (ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expense) {
            expenseDao.insert(expense [0]);
            return null;
        }
    }

    private static class UpdateExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {
        private ExpenseDao expenseDao;
        private UpdateExpenseAsyncTask (ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expense) {
            expenseDao.update(expense [0]);
            return null;
        }
    }

    private static class DeleteExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {
        private ExpenseDao expenseDao;
        private DeleteExpenseAsyncTask (ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expense) {
            expenseDao.delete(expense [0]);
            return null;
        }
    }

    private static class DeleteAllExpenseAsyncTask extends AsyncTask<Expense, Void, Void> {
        private ExpenseDao expenseDao;
        private DeleteAllExpenseAsyncTask (ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expense) {
            expenseDao.deleteAllExpense();
            return null;
        }
    }

}
