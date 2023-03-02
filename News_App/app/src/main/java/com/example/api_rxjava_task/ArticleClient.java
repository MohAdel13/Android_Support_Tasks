package com.example.api_rxjava_task;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleClient {
    private static ArticleClient INSTANCE;
    public ArticleInterface articleInterface;

    public ArticleClient()
    {
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        articleInterface = retrofit.create(ArticleInterface.class);
    }
    public static ArticleClient getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new ArticleClient();
        }
        return INSTANCE;
    }
}
