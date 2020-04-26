package com.example.budgetexchange.Expenses;

import com.example.budgetexchange.Students;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

public class Expense implements Comparable<Expense>{

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
        expenses.add(new Expense(50.50, "Personal", "26/04/2020"));
        expenses.add(new Expense(41.00, "Food", "28/04/2020"));
        expenses.add(new Expense(75.50, "Miscellaneous", "19/04/2020"));
        expenses.add(new Expense(55.50, "Personal", "30/04/2020"));
        expenses.add(new Expense(221.00, "Food", "29/04/2020"));
        expenses.add(new Expense(20.50, "Miscellaneous", "19/04/2020"));
        expenses.add(new Expense(5.50, "Personal", "30/04/2020"));
        Collections.sort(Expense.expenses);
        return expenses;


    }

    //determines the week in the year a particular date belongs in
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

   //determines the week number of a particular date between the goal start and end dates
    public static int weekIntoGoal(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int week = 0;
        try {
            Date startDate = formatter.parse(Students.searchGoals(Students.currUser).getGoalStartDate());
            Date currDate = formatter.parse(date);
            DateTime dateTime1 = new DateTime(startDate);
            DateTime dateTime2 = new DateTime(currDate);
            week = Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return week;
    }

    //calculates the current week out of the year
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

    //calculates the sum of all expenses of a current user
    public static int getSumOfExpenses(ArrayList<Expense> expenses) {
        int expenseTotal = 0;
        for (Expense a : expenses) {
            if (a.getzID().equals(Students.currUser)) {
                expenseTotal = expenseTotal + (int) a.getAmount();
            }
        }
        return expenseTotal;

    }

    @Override
    public int compareTo(Expense compareExpense) {
        int compareWeeks = ((Expense) compareExpense).getWeek();
        return compareWeeks-this.week;

    }

}
