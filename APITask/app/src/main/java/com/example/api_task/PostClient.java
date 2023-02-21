package com.example.api_task;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    private static PostClient INSTANCE;
    public PostInterface postInterface;

    public static PostClient getINSTANCE() {
        if(INSTANCE == null)
        {
            INSTANCE = new PostClient();
        }
        return INSTANCE;
    }
    public PostClient ()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }
}
