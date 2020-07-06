package com.example.amafood.calender.food.datebase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Set;

@Dao
public interface CalenderFoodDeo {

    @Query("SELECT * from calenderFood WHERE foodDate = :date")
    List<CalenderFoods> getNoteList(String date);

    @Insert
    long insertFood(CalenderFoods calenderFoods);

    @Update
    void updateFood(CalenderFoods calenderFoods);

    @Delete
    void deleteFood(CalenderFoods calenderFoods);
}
