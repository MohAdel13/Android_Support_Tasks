package com.example.api_rxjava_task;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = ArticleModel.class,version = 1,exportSchema = false)
@TypeConverters(SourceConverter.class)
public abstract class ArticleDB extends RoomDatabase {
    private static ArticleDB INSTANCE;
    public abstract ArticleDao articleDao();
    public static ArticleDB getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ArticleDB.class,"article.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
