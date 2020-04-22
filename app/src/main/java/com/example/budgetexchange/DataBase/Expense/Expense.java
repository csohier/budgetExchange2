package com.example.budgetexchange.DataBase.Expense;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "expense")
public class Expense {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "amount")
    private double amount;

    @ColumnInfo (name = "type")
    private String type;

    @ColumnInfo (name = "date")
    private String date;

    @ColumnInfo (name = "description")
    private String description;

    @Ignore
    public Expense (double amount, String type, String date, String description) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;

    }

    public Expense (double amount,String type, String date ) {
        this.amount = amount;
        this.type = type;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
