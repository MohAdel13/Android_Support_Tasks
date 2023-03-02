package com.example.api_task;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostInterface {
    @GET("?key=26189611-305d8ed20ad78b772e9585436")
    Single<Response> getPosts();

    @GET("?key=26189611-305d8ed20ad78b772e9585436")
    Observable<Response> getQuerryPosts(@Query("q") String query);

}
