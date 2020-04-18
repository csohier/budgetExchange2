package com.example.budgetexchange.Expenses;

import com.example.budgetexchange.Students;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Expense {

    private String zID;
    private double amount;
    private String type;
    private int week;
    private String date;
    private String description;
    public static ArrayList<Expense> expenses = new ArrayList<>();
    public static int currentWeek;

    public Expense() {
    }

    public Expense(double amount, String type, String date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        week = weekCreate(date);
        zID = Students.currUser;
    }

    public Expense(double amount, String type, String date, String description) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
        week = weekCreate(date);
        zID = Students.currUser;


    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getzID() {
        return zID;
    }

    public void setzID(String zID) {
        this.zID = zID;
    }

    public static ArrayList<Expense> getExpenses() {
        expenses = new ArrayList<>();
        expenses.add(new Expense(201.00, "Food", "04/04/2020"));
        expenses.add(new Expense(75.50, "Miscellaneous", "11/04/2020"));
        expenses.add(new Expense(55.50, "Personal", "18/04/2020"));

        return expenses;


    }


    private int weekCreate(String date) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = date;
        int week = 0;
        try {
            Date newDate = formatter.parse(dateString);
            calendar.setTime(newDate);
            System.out.println("Week number:" +
                    calendar.get(Calendar.WEEK_OF_YEAR));
            week = calendar.get(Calendar.WEEK_OF_YEAR);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }

    public static int currentWeek() throws ParseException {
        Calendar calendar = new GregorianCalendar();
        Date newDate = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(newDate);
        System.out.println("Week CURRENT :" +
                cl.get(Calendar.WEEK_OF_YEAR));
        currentWeek = cl.get(Calendar.WEEK_OF_YEAR);
        return currentWeek;

    }

    public static int getSumOfExpenses(ArrayList<Expense> expenses) {
        int expenseTotal = 0;
        for (Expense a : expenses) {
            if (a.getzID().equals(Students.currUser)) {
                expenseTotal = expenseTotal + (int) a.getAmount();
            }
        }
        return expenseTotal;

    }

}
