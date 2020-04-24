package com.example.budgetexchange.DataBase.Goals;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GoalsDao {

    @Query("SELECT * FROM goals")
    List<Goals> getAllGoals();

    @Insert
    void insert (Goals goals);

    @Update
    void update (Goals goals);

    @Delete
    void delete (Goals goals);

    @Query("DELETE FROM goals")
    void deleteAllGoals();

}
