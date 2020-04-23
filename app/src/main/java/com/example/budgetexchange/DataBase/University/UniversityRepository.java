package com.example.budgetexchange.DataBase.University;

import android.app.Application;
import android.os.AsyncTask;


import java.util.List;

public class UniversityRepository {
    private UniversityDao universityDao;
    private List<University> allUniversity;
    private UniversityDB mDb;

    public UniversityRepository (Application application ) {
        UniversityDB db = UniversityDB.getInstance(application);
        universityDao = db.universityDao();


    }

    public void insert (University university) {
        new InsertUniversityAsyncTask(universityDao).execute(university);
    }

    public void update (University university) {
        new UpdateUniversityAsyncTask(universityDao).execute(university);
    }

    public void delete (University university) {
        new DeleteUniversityAsyncTask(universityDao).execute(university);
    }

    public void deleteAllUniversity() {

    }

    public List<University> getAllUniversity() {
        return allUniversity;
    }

    private static class InsertUniversityAsyncTask extends AsyncTask<University, Void, Void> {
        private UniversityDao universityDao;

        private InsertUniversityAsyncTask (UniversityDao universityDao) {
            this.universityDao = universityDao;
        }

        @Override
        protected Void doInBackground(University... university) {
            universityDao.insert(university[0]);
            return null;
        }
    }

    private static class UpdateUniversityAsyncTask extends AsyncTask<University, Void, Void> {
        private UniversityDao universityDao;

        private UpdateUniversityAsyncTask (UniversityDao universityDao) {
            this.universityDao = universityDao;
        }

        @Override
        protected Void doInBackground(University... university) {
            universityDao.update(university[0]);
            return null;
        }
    }

    private static class DeleteUniversityAsyncTask extends AsyncTask<University, Void, Void> {
        private UniversityDao universityDao;

        private DeleteUniversityAsyncTask (UniversityDao universityDao) {
            this.universityDao = universityDao;
        }

        @Override
        protected Void doInBackground(University... university) {
            universityDao.delete(university[0]);
            return null;
        }
    }

    private static class DeleteAllUniversityAsyncTask extends AsyncTask<Void, Void, Void> {
        private UniversityDao universityDao;

        private DeleteAllUniversityAsyncTask (UniversityDao universityDao) {
            this.universityDao = universityDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            universityDao.deleteAllUniversity();
            return null;
        }
    }
}
