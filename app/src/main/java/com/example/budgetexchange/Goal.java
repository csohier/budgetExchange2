package com.example.budgetexchange;

import com.example.budgetexchange.Expenses.Expense;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Goal {

    private String zID;
    private int income;
    private int goal;
    private String goalEndDate;
    private String goalStartDate;
    private int weeks;
    private int weeksIntoGoal;

    public static int totalSaved;

    public Goal(String zID, int income, int goal, String goalStartDate, String goalEndDate) {
        this.zID = zID;
        this.income = income;
        this.goal = goal;
        this.goalStartDate = goalStartDate;
        this.goalEndDate = goalEndDate;
        weeks = getWeeks(goalStartDate,goalEndDate);
        weeksIntoGoal = getWeeksIn(goalStartDate);
        //totalSaved=calculateSaved(zID);
    }

    public  int getTotalSaved() {
        return totalSaved;
    }

    public  void setTotalSaved(int totalSaved) {
        this.totalSaved = totalSaved;
    }


    public String getzID() {
        return zID;
    }

    public int getWeeksIntoGoal() {
        return weeksIntoGoal;
    }

    public void setWeeksIntoGoal(int weeksIntoGoal) {
        this.weeksIntoGoal = weeksIntoGoal;
    }

    public void setzID(String zID) {
        this.zID = zID;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getGoalEndDate() {
        return goalEndDate;
    }

    public void setGoalEndDate(String goalDate) {
        this.goalEndDate = goalEndDate;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public String getGoalStartDate() {
        return goalStartDate;
    }

    public void setGoalStartDate(String goalStartDate) {
        this.goalStartDate = goalStartDate;
    }

    //dummy data for goals
    public static ArrayList<Goal> getGoals() {
        Students.goals = new ArrayList<>();
        Students.goals.add(new Goal("z0000000", 350, 8000, "01/03/2020", "14/08/2020"));
        Students.goals.add(new Goal("z5431234", 500, 7000, "22/09/2019", "22/12/2019"));
        return Students.goals;
    }

    public static void addGoal(Goal entry) {
        Students.goals.add(entry);
    }


    //Calculates the number of weeks between a goal start and end date
    public int getWeeks(String goalStartDate, String goalEndDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int week=0;
        try {
            Date startDate = formatter.parse(goalStartDate);
            Date endDate = formatter.parse(goalEndDate);
            DateTime dateTime1 = new DateTime(startDate);
            DateTime dateTime2 = new DateTime(endDate);
            week=Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return week;
    }

    //calculates how many weeks into a goal a particular date is
    public int getWeeksIn(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int week=0;
        try {
            Date startDate = formatter.parse(goalStartDate);
            Date currDate = new Date();
            DateTime dateTime1 = new DateTime(startDate);
            DateTime dateTime2 = new DateTime(currDate);
            week=Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
        }catch(ParseException e){ e.printStackTrace(); }
        return week;
    }

    //calculates the percentage that has been saved by the current user
    public static int percentageSaved(int total){
        Goal user = Students.searchGoals(Students.currUser);
        double percentage = ((double)total/(double)user.getGoal())*100;
        int castPercentage = (int)percentage;
        return castPercentage;
    }

    public static int calculateTotalSaved(String studentID) {
        Goal user = Students.searchGoals(studentID);

        for (Goal a : Students.goals) {
            if (a.getzID().equals(studentID)) {
                totalSaved = (user.getIncome() * user.getWeeksIntoGoal()) - Expense.getSumOfExpenses(Expense.expenses);
            }
        }
        return totalSaved;
    }
}
