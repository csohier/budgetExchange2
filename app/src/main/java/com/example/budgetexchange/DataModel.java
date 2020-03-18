package com.example.budgetexchange;

import java.util.ArrayList;

public class DataModel {

    private String fName;
    private String lName;
    private String zID;
    private String email;
    private String password;
    private String country;

    public DataModel(String fName, String lName, String zID, String email, String password, String country) {
        this.fName = fName;
        this.lName = lName;
        this.zID = zID;
        this.email = email;
        this.password = password;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static ArrayList<DataModel> getStudents() {
        ArrayList<DataModel> students = new ArrayList<>();
        students.add(new DataModel("Erica", "Ma", "z5435934", "erica.ma@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Hanson", "Lee", "z5431234", "hanson.lee@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Jerry", "Yin", "z5115643", "jerry.yin@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Emily", "Scott", "z6352556", "emily.scott@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Wendy", "Lee", "z6335632", "wendy.lee@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Mark", "Bradley", "z6423154", "mark.bradley@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Eric", "Park", "z6425797", "eric.park@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Hannah", "Parker", "z6743566", "hannah.parker@student.unsw.edu.au", "Ema123", "England"));
        students.add(new DataModel("Sarah", "Li", "z6234971", "sarah.li@student.unsw.edu.au", "Ema123", "England"));
        return students;
    }

    public static DataModel searchStudents(int position) {
        for(int i = 0; i > getStudents().size(); i++)
            if(i==position){
                return getStudents().get(i);
            }
        return null;
    }
}
