package com.example.budgetexchange;

import com.example.budgetexchange.Expenses.Expense;

import java.util.ArrayList;
import java.util.Collections;

public class Achievements {
    private String name;
    private int percentTrigger;
    private int imageResource;
    public static ArrayList<Achievements> achievementsList;

    public Achievements(){

    }

    public Achievements(String name, int percentTrigger) {
        this.name = name;
        this.percentTrigger = percentTrigger;
        imageResource=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentTrigger() {
        return percentTrigger;
    }

    public void setPercentTrigger(int percentTrigger) {
        this.percentTrigger = percentTrigger;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public static ArrayList<Achievements> getAchievements() {
        achievementsList = new ArrayList<>();
        achievementsList.add(new Achievements("Novice", 10));
        achievementsList.add(new Achievements("Beginner", 25));
        achievementsList.add(new Achievements("Intermediate",50));
        achievementsList.add(new Achievements("Advanced", 80));
        achievementsList.add(new Achievements("Superior", 100));
        return achievementsList;

    }
}
