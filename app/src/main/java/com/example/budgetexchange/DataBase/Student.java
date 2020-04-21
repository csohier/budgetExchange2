package com.example.budgetexchange.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Student {
    @Entity (tableName = "studentDB")
    public class studentDB {

        @PrimaryKey (autoGenerate =  true)
        private String zID;

        @ColumnInfo (name = "fName")
        private String fName;

        @ColumnInfo (name = "lName")
        private String lName;

        @ColumnInfo (name = "password")
        private String password;

        @ColumnInfo (name = "email")
        private String email;

        @ColumnInfo (name = "discipline")
        private String discipline;

        @ColumnInfo (name = "university")
        private String university;

        @ColumnInfo (name = "stDate")
        private String stDate;

        @ColumnInfo (name = "wkIncome")
        private float wkIncome;


        public studentDB (String zID, String fName, String lName, String password, String email, String discipline, String  university, String stDate, float wkIncome) {
            this.zID = zID;
            this.fName = fName;
            this.lName = lName;
            this.password = password;
            this.email = email;
            this.discipline = discipline;
            this.university = university;
            this.stDate = stDate;
            this.wkIncome = wkIncome;

        }

        public String getzID() {
            return zID;
        }

        public void setzID(String zID) {
            this.zID = zID;
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

        public String getDiscipline() {
            return discipline;
        }

        public void setDiscipline(String discipline) {
            this.discipline = discipline;
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

    }

}
