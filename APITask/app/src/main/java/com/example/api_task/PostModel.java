package com.example.api_task;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "POST_TABLE")
public class PostModel {
    @PrimaryKey
    int id;
    String pageURL;
    String type;
    String tags;
    String previewURL;
    int previewWidth;
    int previewHeight;
    String webformatURL;
    int webformatWidth;
    int webformatHeight;
    String largeImageURL;
    String fullHDURL;
    String imageURL;
    int imageWidth;
    int imageHeight;
    int imageSize;
    int views;
    int downloads;
    int likes;
    int comments;
    int user_id;
    String user;
    String userImageURL;
}
