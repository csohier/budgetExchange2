package com.example.budgetexchange.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class University {
    @Entity(tableName = "universityDB")
    public class universityDB {
        @PrimaryKey(autoGenerate = true)
        private String name;

        @ColumnInfo(name = "discipline")
        private String discipline;

        @ColumnInfo(name = "country")
        private String country;

        @ColumnInfo(name = "region")
        private String region;

        @ColumnInfo(name = "currency")
        private String currency;


        public universityDB(String name, String discipline, String country, String region, String currency) {
            this.name = name;
            this.discipline = discipline;
            this.country = country;
            this.region = region;
            this.currency = currency;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDiscipline() {
            return discipline;
        }

        public void setDiscipline(String discipline) {
            this.discipline = discipline;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
}
