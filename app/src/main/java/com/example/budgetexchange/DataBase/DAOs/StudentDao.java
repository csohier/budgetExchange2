package com.example.budgetexchange.DataBase.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetexchange.DataBase.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("Select * from studentDB")
    List<Student> getStudentDBList();

    @Insert
    void insertStudent (Student student);

    @Update
    void updateStudent (Student student);

    @Delete
    void deleteStudent (Student student);


}
