package com.example.budgetexchange.DataBase.Social;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SocialRepository {
    private SocialDao socialDao;
    private LiveData<List<Social>> allSocial;

    public SocialRepository (Application application) {
        SocialDB db = SocialDB.getInstance(application);
        socialDao = db.socialDao();

    }

    public void insert (Social social) {
        new InsertSocialAsyncTask(socialDao).execute(social);
    }

    public void update (Social social) {
        new UpdateSocialAsyncTask(socialDao).execute(social);
    }

    public void delete (Social social) {
        new DeleteSocialAsyncTask(socialDao).execute(social);
    }

    public void deleteAllSocial() {

    }

    public LiveData<List<Social>> getAllSocial() {
        return allSocial;
    }

    private static class InsertSocialAsyncTask extends AsyncTask<Social, Void, Void> {
        private SocialDao socialDao;

        private InsertSocialAsyncTask (SocialDao socialDao) {
            this.socialDao = socialDao;
        }

        @Override
        protected Void doInBackground(Social... social) {
            socialDao.insert(social[0]);
            return null;
        }
    }

    private static class UpdateSocialAsyncTask extends AsyncTask<Social, Void, Void> {
        private SocialDao socialDao;

        private UpdateSocialAsyncTask (SocialDao socialDao) {
            this.socialDao = socialDao;
        }

        @Override
        protected Void doInBackground(Social... social) {
            socialDao.update(social[0]);
            return null;
        }
    }

    private static class DeleteSocialAsyncTask extends AsyncTask<Social, Void, Void> {
        private SocialDao socialDao;

        private DeleteSocialAsyncTask (SocialDao socialDao) {
            this.socialDao = socialDao;
        }

        @Override
        protected Void doInBackground(Social... social) {
            socialDao.delete(social[0]);
            return null;
        }
    }

    private static class DeleteAllSocialAsyncTask extends AsyncTask<Social, Void, Void> {
        private SocialDao socialDao;

        private DeleteAllSocialAsyncTask (SocialDao socialDao) {
            this.socialDao = socialDao;
        }

        @Override
        protected Void doInBackground(Social... social) {
            socialDao.deleteAllSocial();
            return null;
        }
    }
}
