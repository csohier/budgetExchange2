package com.example.budgetexchange.DataBase.Social;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SocialDao {
    @Query("SELECT * FROM Social")
    LiveData<List<Social>> getAllSocial();

    @Insert
    void insert (Social social);

    @Update
    void update (Social social);

    @Delete
    void delete (Social social);

    @Query("DELETE FROM Social")
    void deleteAllSocial();
}
