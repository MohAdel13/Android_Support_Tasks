package com.example.api_rxjava_task;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     Completable insertArticle(ArticleModel article);

    @Delete
    Completable deleteArticle(ArticleModel article);

    @Query("SELECT * FROM ARTICLE_TABLE")
    Single<List<ArticleModel>> getAllArticles();
}
