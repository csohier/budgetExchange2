package com.example.budgetexchange;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Students {

    private String fName;
    private String lName;
    private String password;
    private String zID;
    private String email;
    private String discipline;
    private String university;
    private String stDate;
    private Float wkIncome;


    public Students () {
        //empty constructor
    }

    public Students(String fName, String lName, String password, String zID, String email, String discipline, String university, String stDate, Float wkIncome) {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.zID = zID;
        this.email = email;
        this.discipline = discipline;
        this.university=university;
        this.stDate = stDate;
        this.wkIncome = wkIncome;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getzID() {
        return zID;
    }

    public void setzID(String zID) {
        this.zID = zID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniversity() {
        return university;
    }

    public String getDiscipline() { return discipline; }

    public void setDiscipline(String discipline) { this.discipline = discipline; }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getStDate() { return DateFormat.getDateInstance().format(stDate); }

    public void setStDate(String stDate) { this.stDate = stDate; }

    public Float getWkIncome() { return wkIncome; }

    public void setWkIncome(Float wkIncome) { this.wkIncome = wkIncome; }

    public static ArrayList<Students> getStudents() {
        ArrayList <Students> students = new ArrayList<>();

        students.add(new Students("Corona", "Virus", "COVID-19", "z0000000", "c.virus@student.unsw.edu.au", "Economics", "London School of Economics", "31/03/2020",1000f));

        return students;
    }

    public static Students searchStudents(int position) {
        for(int i = 0; i > getStudents().size(); i++)
            if(i==position){
                return getStudents().get(i);
            }
        return null;
    }

    public static void addStudents(String fName, String lName, String password, String zID, String email, String discipline, String university, String stDate, double wkIncome) {
    }

    public static void addStudents (Students entry){
        Students.addStudents(entry);
    }
}
