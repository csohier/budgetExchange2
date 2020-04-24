package com.example.budgetexchange.DataBase.Achievements;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "achievements")
public class Achievements implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @ColumnInfo (name = "percentTrigger")
    private int percentTrigger;

    @ColumnInfo (name = "imageResource")
    private int imageResource;

    public Achievements (String name, int percentTrigger) {
        this.name = name;
        this.percentTrigger = percentTrigger;
        imageResource=0;
    }
}
