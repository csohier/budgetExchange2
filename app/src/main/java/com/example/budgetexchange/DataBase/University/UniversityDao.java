package com.example.budgetexchange.DataBase.University;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UniversityDao {
        @Query("Select * from university")
        List<University> allUniversity();

        @Insert
        void insert (University university);

        @Update
        void update (University university);

        @Delete
        void delete (University university);

        @Query("DELETE FROM university")
        void deleteAllUniversity();
/*
        @Query("SELECT name FROM university")
        University getUniversity (String name); */


}
