package com.example.budgetexchange.DataBase.Student;

import android.os.AsyncTask;

import com.example.budgetexchange.DataBase.AppDatabase;

import java.util.List;

public class GetAllStudentAsyncTask extends AsyncTask<Void, Integer, List<Student>> {

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
    protected List<Student> doInBackground(Void... voids) { return db.studentDao().getAllStudent(); }

    @Override
    protected void onPostExecute(List<Student> result) {
        delegate.handleGetAllStudentsResult(result);
    }

}
