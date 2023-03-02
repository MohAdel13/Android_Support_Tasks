package com.example.api_rxjava_task;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class SourceConverter {
    @TypeConverter
    public SourceModel GsonToSource(String s)
    {
        return new Gson().fromJson(s,SourceModel.class);
    }

    @TypeConverter
    public String FromSource(SourceModel s)
    {
        return new Gson().toJson(s);
    }
}
