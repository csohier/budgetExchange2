package com.example.budgetexchange.DataBase.Social;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "social")
public class Social {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "title" )
    private String title;

    @ColumnInfo (name = "content")
    private String content;

    @ColumnInfo (name = "zID")
    private String zID;

    @ColumnInfo (name = "postDate")
    private String postDate;

    @ColumnInfo (name = "firstName")
    private String firstName;

    @ColumnInfo (name = "lastName")
    private String lastName;

    @ColumnInfo (name = "type")
    private String type;

    public Social (String title, String content, String zID, String firstName, String lastName, String postDate, String type) {
        this.title = title;
        this.content = content;
        this.zID = zID;
        this.postDate = postDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type=type;

    }

}
