package com.example.budgetexchange.DataBase.Social;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Social.class, exportSchema = false, version = 1)
public abstract class SocialDB extends RoomDatabase {
    private static SocialDB instance;

    public abstract SocialDao socialDao();
    public static synchronized SocialDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SocialDB.class,
                    "social_db")
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
        private SocialDao socialDao;

        private PopulateDbAsyncTask (SocialDB db) {
            socialDao = db.socialDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            socialDao.insert(new Social("$5 Pasta Recipe", "content","z0000000","Corona","Virus","03/04/2020", "Cheap Eats"));
            return null;
        }
    }


}
