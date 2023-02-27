package com.example.api_task;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = PostModel.class,version = 1,exportSchema = false)
public abstract class PostDB extends RoomDatabase {
    private static PostDB INSTANCE;
    public abstract PostDao postDao();
    public static PostDB getInstance(Context context)
    {
        if(INSTANCE==null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PostDB.class,"post.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
