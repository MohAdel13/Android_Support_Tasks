package com.example.api_task;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.api_task.PostModel;

import java.util.List;

@Dao
public interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(PostModel post);

    @Query("SELECT * FROM POST_TABLE")
    List<PostModel> getAllPosts();

    @Delete
    void deletePost(PostModel post);
}
