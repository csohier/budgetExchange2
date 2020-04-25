package com.example.budgetexchange.DataBase.Student;

import android.os.AsyncTask;

import com.example.budgetexchange.DataBase.AppDatabase;

import java.util.List;

public class GetAllZIDAsyncTask extends AsyncTask<Void, Integer, List<String>> {

    private AsyncTaskStudentDelegate delegate;
    private AppDatabase db;

    public void setDelegate(AsyncTaskStudentDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        return db.studentDao().getZIDs();
    }

    @Override
    protected void onPostExecute(List<String> result) {
        delegate.handleGetZIDResult(result);
    }
}