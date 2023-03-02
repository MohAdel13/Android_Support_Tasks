package com.example.api_rxjava_task;

import java.util.List;

public class Response {
    String status;
    int totalResults;
    List<ArticleModel> articles;

    public List<ArticleModel> getArticles() {
        return articles;
    }
}
