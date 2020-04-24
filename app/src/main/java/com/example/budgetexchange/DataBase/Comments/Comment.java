package com.example.budgetexchange.DataBase.Comments;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "comment")
public class Comment implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo (name = "date")
    private String date;

    @ColumnInfo (name = "content")
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment (int id, String date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
    }

}
