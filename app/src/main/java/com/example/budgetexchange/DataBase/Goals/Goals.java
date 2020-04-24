package com.example.budgetexchange.DataBase.Goals;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.budgetexchange.DataBase.Student.Student;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity (tableName = "goals")
public class Goals implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "ZID")
    private String ZID;

    @ColumnInfo (name = "income")
    private int income;

    @ColumnInfo (name = "goal")
    private int goal;

    @ColumnInfo (name = "goalEndDate")
    private String goalEndDate;

    @ColumnInfo (name = "goalStartDate")
    private String goalStartDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZID() {
        return ZID;
    }

    public void setzID(String ZID) {
        this.ZID = ZID;
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

    public void setGoalEndDate(String goalEndDate) {
        this.goalEndDate = goalEndDate;
    }

    public String getGoalStartDate() {
        return goalStartDate;
    }

    public void setGoalStartDate(String goalStartDate) {
        this.goalStartDate = goalStartDate;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getWeeksIntoGoal() {
        return weeksIntoGoal;
    }

    public void setWeeksIntoGoal(int weeksIntoGoal) {
        this.weeksIntoGoal = weeksIntoGoal;
    }

    @ColumnInfo (name = "weeks")
    private int weeks;

    @ColumnInfo (name = "weeksIntoGoal")
    private int weeksIntoGoal;

    public Goals(String ZID, int income, int goal, String goalStartDate, String goalEndDate) {
        this.ZID = ZID;
        this.income = income;
        this.goal = goal;
        this.goalStartDate = goalStartDate;
        this.goalEndDate = goalEndDate;
        weeks = getWeeks(goalStartDate,goalEndDate);
        weeksIntoGoal = getWeeksIn(goalStartDate);
        //totalSaved =0;
    }

    public int getWeeks(String goalStartDate, String goalEndDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int week=0;
        try {
            Date startDate = formatter.parse(goalStartDate);
            Date endDate = formatter.parse(goalEndDate);
            DateTime dateTime1 = new DateTime(startDate);
            DateTime dateTime2 = new DateTime(endDate);
            week= Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return week;
    }

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

    /*public static int percentageSaved(){
        Goals user = Student.searchGoals(Student.currUser);
        double percentage = (user.getTotalSaved()/user.getGoal())*100;
        int castPercentage = (int)percentage;
        return castPercentage;
    }*/

}
