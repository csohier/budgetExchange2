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

    @ColumnInfo (name = "ZID")
    private String ZID;

    @ColumnInfo (name = "postDate")
    private String postDate;

    @ColumnInfo (name = "firstName")
    private String firstName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getZID() {
        return ZID;
    }

    public void setZID(String ZID) {
        this.ZID = ZID;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ColumnInfo (name = "lastName")
    private String lastName;

    @ColumnInfo (name = "type")
    private String type;

    public Social (String title, String content, String ZID, String firstName, String lastName, String postDate, String type) {
        this.title = title;
        this.content = content;
        this.ZID = ZID;
        this.postDate = postDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type=type;

    }

}
