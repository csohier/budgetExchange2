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
