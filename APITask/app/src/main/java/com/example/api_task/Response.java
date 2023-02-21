package com.example.api_task;

import java.util.List;

public class Response {
    private int total;
    private int totalHits;
    List<PostModel> hits;

    public List<PostModel> getHits() {
        return hits;
    }
}
