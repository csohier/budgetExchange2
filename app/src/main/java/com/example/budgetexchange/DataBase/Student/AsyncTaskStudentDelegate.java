package com.example.budgetexchange.DataBase.Student;

import java.util.List;

public interface AsyncTaskStudentDelegate {
    void handleInsertStudentResult(String result);
    void handleGetStudentResult(Student student);
    void handleGetAllStudentsResult(List<Student> student);
    void handleGetZIDResult(List<String> zID);
    void handleGetStudentByZID(Student zID);

    void handleUpdateStudentByZID (String result);

    //Add Mastery handle*

}

