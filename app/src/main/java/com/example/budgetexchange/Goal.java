package com.example.budgetexchange;

import java.util.ArrayList;
import java.util.Date;

public class Goal {

    private String zID;
    private int income;
    private int goal;
    private String goalDate;
    public static ArrayList<Goal> goals;

    public Goal(String zID, int income, int goal, String goalDate) {
        this.zID = zID;
        this.income = income;
        this.goal = goal;
        this.goalDate = goalDate;
    }

    public String getzID() {
        return zID;
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

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public static ArrayList<Goal> getGoals (){
        goals = new ArrayList<>();
        goals.add(new Goal("z5435934",350,8000,"25/07/2020"));
        goals.add(new Goal("z5431234",500,7000,"22/09/2020"));
        return goals;
    }

    public static Goal searchGoals(int position) {
        for(int i = 0; i > getGoals().size(); i++)
            if(i==position){
                return getGoals().get(i);
            }
        return null;
    }

    public static void addGoal(Goal entry){
        goals.add(entry);
    }
}
