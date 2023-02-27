package com.example.api_task;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    private static PostClient INSTANCE;
    public PostInterface postInterface;

    public PostClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostClient getINSTANCE()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new PostClient();
        }
        return INSTANCE;
    }
}
