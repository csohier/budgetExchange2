package com.example.budgetexchange.DataBase.Student;

import android.os.AsyncTask;
import com.example.budgetexchange.DataBase.AppDatabase;

//This task inserts the Student into the AppDatabase
public class InsertStudentAsyncTask extends AsyncTask<Student, Integer, String> {

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
    protected String doInBackground(Student...students) {

        db.studentDao().insert(students[0]);
        // When the task is finished, it will return.
        // You would normally want to return the result of your task.
        // For example, if my task was to get zIDs from DB, I would make this method return the list
        // of zIDs. The return value goes straight to onPostExecute.
        return "Successfully registered!";
    }

    @Override
    protected void onPostExecute(String result) { delegate.handleInsertStudentResult(result); }
}
