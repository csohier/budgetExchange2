package com.example.budgetexchange;

import java.util.List;

public class Expense {

    private double amount;
    private String type;
    private int week;
    private String date;
    public static List<Expense> expenses;

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
}
