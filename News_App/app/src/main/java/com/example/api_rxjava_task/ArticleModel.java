package com.example.api_rxjava_task;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity(tableName = "ARTICLE_TABLE")
public class ArticleModel {
    SourceModel source;
    String author;
    @PrimaryKey
            @NonNull
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    String content;
}
