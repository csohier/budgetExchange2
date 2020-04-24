package com.example.budgetexchange.DataBase.Comments;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommentDao {
    @Query("SELECT * FROM comment")
    List<Comment> getAllComment();

    @Update
    void update (Comment comment);

    @Delete
    void delete (Comment comment);

    @Query("DELETE FROM comment")
    void deleteAllComment();

}
