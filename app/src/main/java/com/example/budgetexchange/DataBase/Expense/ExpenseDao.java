package com.example.budgetexchange.DataBase.Expense;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM expense")
    LiveData<List<Expense>> getAllExpense();

    @Insert
    void insert (Expense expense);

    @Update
    void update (Expense expense);

    @Query("DELETE FROM expense")
    void deleteAllExpense();

}
