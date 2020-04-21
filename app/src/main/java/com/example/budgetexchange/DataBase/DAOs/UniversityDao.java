package com.example.budgetexchange.DataBase.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetexchange.DataBase.University;

import java.util.List;

@Dao
public interface UniversityDao {
        @Query("Select * from universityDB")
        List<University> getS();

        @Insert
        void insertUniversity (University university);

        @Update
        void updateUniversity (University university);

        @Delete
        void deleteUniversity (University university);


}
