package com.example.api_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.api_task.databinding.ActivityFavPostsBinding;

import java.util.ArrayList;
import java.util.List;

public class FavPostsActivity extends AppCompatActivity {
    ActivityFavPostsBinding binding;
    PostDB db;
    List<PostModel> posts;
    FavPostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityFavPostsBinding.inflate(LayoutInflater.from(getApplicationContext()));
        setContentView(binding.getRoot());

        db = PostDB.getInstance(getApplicationContext());
        posts = new ArrayList<>();
        adapter = new FavPostAdapter(getApplicationContext());

        binding.backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        new getAsync().execute();
    }
    private class getAsync extends AsyncTask<Void,Void,List<PostModel>>
    {

        @Override
        protected List<PostModel> doInBackground(Void... voids) {
            posts = db.postDao().getAllPosts();
            return posts;
        }

        @Override
        protected void onPostExecute(List<PostModel> postModels) {
            super.onPostExecute(postModels);
            adapter.setPosts(postModels);
            binding.postRV2.setAdapter(adapter);
        }
    }
}