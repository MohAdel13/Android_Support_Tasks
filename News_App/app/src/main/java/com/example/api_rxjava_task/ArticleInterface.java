package com.example.api_rxjava_task;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleInterface {

    @GET("top-headlines?country=us&apiKey=7098498f6ab64a9680cc9aba8ee66ae5")
    Single<Response> getArticles();

    @GET("everything?apiKey=7098498f6ab64a9680cc9aba8ee66ae5")
    Observable<Response> getQueryArticles(@Query("q") String query);
}
