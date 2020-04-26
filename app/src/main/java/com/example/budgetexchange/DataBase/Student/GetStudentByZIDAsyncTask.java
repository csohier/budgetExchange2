package com.example.budgetexchange.DataBase.Student;

import android.os.AsyncTask;
import com.example.budgetexchange.DataBase.AppDatabase;

//This task returns the relevant Student by the zID within the AppDatabase
public class GetStudentByZIDAsyncTask extends AsyncTask<String, Integer, Student> {

    private AsyncTaskStudentDelegate delegate;
    private AppDatabase db;

    public void setDelegate(AsyncTaskStudentDelegate delegate) { this.delegate = delegate; }

    public void setDatabase(AppDatabase db) { this.db = db; }

    @Override
    protected void onPreExecute() { super.onPreExecute(); }

    @Override
    protected Student doInBackground(String... strings) { return db.studentDao().getStudentByZID(strings[0]); }

    @Override
    protected void onPostExecute(Student result) { delegate.handleGetStudentByZID(result); }
}


