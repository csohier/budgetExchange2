package com.example.budgetexchange;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Expense {

    private double amount;
    private String type;
    private int week;
    private String date;
    public static ArrayList<Expense> expenses;

    public Expense() {
    }

    public Expense(double amount, String type, int week, String date) {
        this.amount = amount;
        this.type = type;
        this.week = week;
        this.date = date;
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

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static ArrayList<Expense> getExpenses (){
        expenses = new ArrayList<>();
        expenses.add(new Expense(201.00,"Food",1,"04/04/2020"));
        expenses.add(new Expense(75.50,"Leisure",2, "11/04/2020"));
        expenses.add(new Expense(55.50,"Fees",3, "18/04/2020"));
        return expenses;

    }
}
