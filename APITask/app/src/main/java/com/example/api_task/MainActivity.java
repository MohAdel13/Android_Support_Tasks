package com.example.api_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.api_task.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<PostModel> posts;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(getApplicationContext()));
        setContentView(binding.getRoot());
        adapter = new PostAdapter(getApplicationContext());
        posts = new ArrayList<>();
        Call<List<PostModel>> call = PostClient.getINSTANCE().postInterface.getPosts();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                posts = response.body();
                adapter.setPosts(posts);
                binding.postRV.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"IamDone",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"OnFailure: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}