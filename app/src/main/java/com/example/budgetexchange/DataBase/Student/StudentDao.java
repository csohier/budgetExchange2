package com.example.budgetexchange.DataBase.Student;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAllStudent();

    //Adds a Student
    @Insert
    void insert (Student student);

    //Updates the details of a current student
    @Update
    void update (Student student);

    //Gets the student by zID
    @Query("SELECT * FROM student where ZID = :zID")
    Student getStudentByZID (String zID);

    //Search if student exists already
    @Query("SELECT ZID FROM student")
    List<String> getZIDs();

}
