package com.example.budgetexchange.DataBase.Student;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAllStudent();

    @Insert
    void insertAll(Student... students);

    @Insert
    void insert (Student student);

    @Update
    void update (Student student);

    @Delete
    void delete (Student student);

    @Delete
    void deleteAll(Student... students);


    @Query("SELECT * FROM student WHERE ZID = :zID and password = :password")
    List<Student> getStudent(String zID, String password);

    //Gets the student by zID
    @Query("SELECT * FROM student where ZID = :zID")
    Student getStudentByZID (String zID);

    //Search if student exists already
    @Query("SELECT ZID FROM student")
    List<String> getZIDs();

    //Add Mastery Points Query here**


}
