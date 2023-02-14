package com.example.roomdatabase_task.pojo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = CarModel.class,version = 1,exportSchema = false)
public abstract class CarDB extends RoomDatabase {
    public abstract CarDao carDao();
    private static CarDB Instance;
    public static CarDB getInstance(final Context context)
    {
        if(Instance == null) {
            Instance = Room.databaseBuilder(context.getApplicationContext(),CarDB.class,"car.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return Instance;
    }
}
