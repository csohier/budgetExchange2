package com.example.budgetexchange.DataBase.Student;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//This is the model class for Student
@Entity (tableName = "student")
public class Student {

    @PrimaryKey (autoGenerate =  true)
    @NonNull
    private int id;

    @ColumnInfo (name = "ZID")
    private String ZID;

    @ColumnInfo (name = "FName")
    private String FName;

    @ColumnInfo (name = "LName")
    private String LName;

    @ColumnInfo (name = "password")
    private String password;

    @ColumnInfo (name = "email")
    private String email;

    @ColumnInfo (name = "university")
    private String university;

    @ColumnInfo (name = "stDate")
    private String stDate;

    @ColumnInfo (name = "wkIncome")
    private float wkIncome;

    public static String currUser;

    //Constructor for Student
    public Student (String ZID,
                    String FName,
                    String LName,
                    String password,
                    String email,
                    String  university,
                    String stDate,
                    float wkIncome)
    {
        this.ZID = ZID;
        this.FName = FName;
        this.LName = LName;
        this.password = password;
        this.email = email;
        this.university = university;
        this.stDate = stDate;
        this.wkIncome = wkIncome;
    }

    public String getZID() {
        return ZID;
    }

    public void setZID(String zID) {
        this.ZID = zID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String fName) {
        this.FName = fName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String lName) {
        this.LName = lName;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public float getWkIncome() {
        return wkIncome;
    }

    public void setWkIncome(float wkIncome) {
        this.wkIncome = wkIncome;
    }

    public static String getCurrUser() { return currUser; }

    public static void setCurrUser(String currUser) { Student.currUser = currUser; }

}


