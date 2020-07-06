package com.example.amafood.calender.food.datebase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CalenderFoods.class},version = 1,exportSchema = false)
public abstract class CalenderFoodDataBase extends RoomDatabase {
        public abstract CalenderFoodDeo calenderFoodDeo();
        private static volatile CalenderFoodDataBase instance;
        private static final int NUMBER_OF_THREADS =4;
        static final ExecutorService databaseex = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        public static CalenderFoodDataBase getInstance(final Context context){
            if (instance == null){
                synchronized (CalenderFoodDataBase.class){
                    if (instance == null){
                        instance = Room.databaseBuilder(context.getApplicationContext(),CalenderFoodDataBase.class,"calender_foods_database")
                                .allowMainThreadQueries().build();
                    }
                }
            }
            return instance;
        }
}
