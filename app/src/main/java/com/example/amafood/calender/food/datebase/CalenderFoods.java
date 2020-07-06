package com.example.amafood.calender.food.datebase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calenderFood")
public class CalenderFoods {

    @PrimaryKey(autoGenerate = true)
    private int food_id = 0;

    @ColumnInfo(name = "foodDate")
    private String calenderDate;

    @ColumnInfo(name = "foodName")
    private String calenderFoodName;

    @ColumnInfo(name = "foodImage")
    private String calenderFoodImage;

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public CalenderFoods(String calenderDate, String calenderFoodName, String calenderFoodImage) {
        this.calenderDate = calenderDate;
        this.calenderFoodName = calenderFoodName;
        this.calenderFoodImage = calenderFoodImage;
    }

    public int getFood_id() {
        return food_id;
    }


    public String getCalenderDate() {
        return calenderDate;
    }

    public String getCalenderFoodImage() {
        return calenderFoodImage;
    }

    public String getCalenderFoodName() {
        return calenderFoodName;
    }


}
