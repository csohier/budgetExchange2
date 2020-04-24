package com.example.budgetexchange.DataBase.Achievements;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface AcheivementsDao {
    @Query("SELECT * FROM achievements")
    List<Achievements> getAllAchievements();

    @Insert
    void insert (Achievements achievements);

    @Update
    void update (Achievements achievements);

    @Delete
    void delete (Achievements achievements);

    @Query("DELETE FROM achievements")
    void deleteAllAchievements();

}
