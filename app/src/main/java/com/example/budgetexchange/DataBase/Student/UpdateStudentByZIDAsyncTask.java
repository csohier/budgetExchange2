package com.example.budgetexchange.DataBase.Student;

import android.os.AsyncTask;
import com.example.budgetexchange.DataBase.AppDatabase;

public class UpdateStudentByZIDAsyncTask extends AsyncTask<Student, Integer, String> {

    private AsyncTaskStudentDelegate delegate;
    private AppDatabase db;

    public void setDelegate (AsyncTaskStudentDelegate delegate) {
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
    protected String doInBackground(Student...students) {
        db.studentDao().update(students[0]);
        return "Successfully updated!";
    }

    @Override
    protected void onPostExecute(String result) { delegate.handleUpdateStudentByZID(result); }
}
