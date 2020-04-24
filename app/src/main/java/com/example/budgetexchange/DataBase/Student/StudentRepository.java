package com.example.budgetexchange.DataBase.Student;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.budgetexchange.DataBase.AppDatabase;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudent;

    public StudentRepository (Application application ) {
        AppDatabase db = AppDatabase.getInstance(application);
        studentDao = db.studentDao();
        allStudent = studentDao.getAllStudent();
    }

    public void insert (Student student) {
        new InsertStudentAsyncTask(studentDao).execute(student);
    }

    public void update (Student student) {
        new UpdateStudentAsyncTask(studentDao).execute(student);
    }

    public void delete (Student student) {
        new DeleteStudentAsyncTask(studentDao).execute(student);
    }

    public void deleteAllStudent() {

    }

    public LiveData<List<Student>> getAllStudent() {
        return allStudent;
    }

    public LiveData<Student> getStudent(String name, String password) {
        return studentDao.getStudent(name, password);
    }

    private static class InsertStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private InsertStudentAsyncTask (StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... student) {
            studentDao.insert(student[0]);
            return null;
        }
    }

    private static class UpdateStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private UpdateStudentAsyncTask (StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... student) {
            studentDao.update(student[0]);
            return null;
        }
    }

    private static class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private DeleteStudentAsyncTask (StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... student) {
            studentDao.delete(student[0]);
            return null;
        }
    }

    private static class DeleteAllStudentAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentDao studentDao;

        private DeleteAllStudentAsyncTask (StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudent();
            return null;
        }
    }

    private static class StudentLogin extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;
        private StudentLogin (StudentDao studentDao) {

            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... student) {
            studentDao.deleteAllStudent();
            studentDao.insert(student[0]);
            return null;
        }
    }
}
