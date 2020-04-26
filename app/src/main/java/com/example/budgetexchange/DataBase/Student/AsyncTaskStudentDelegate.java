package com.example.budgetexchange.DataBase.Student;

import java.util.List;

//This consists of all AsyncTasks that needs to be performed for Student in the Workers Thread
public interface AsyncTaskStudentDelegate {
    void handleInsertStudentResult(String result);
    void handleGetAllStudentsResult(List<Student> student);
    void handleGetZIDResult(List<String> zID);
    void handleGetStudentByZID(Student zID);
    void handleUpdateStudentByZID (String result);

}

