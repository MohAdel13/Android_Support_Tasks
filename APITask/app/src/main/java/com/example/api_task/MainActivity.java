package com.example.api_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.api_task.databinding.ActivityMainBinding;
import com.example.api_task.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<PostModel> postsList;
    PostAdapter adapter;
    List<PostModel> favPosts;

    FavPostAdapter favAdapter;
    PostDB db;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMainBinding.inflate(LayoutInflater.from(getApplicationContext()));
        setContentView(binding.getRoot());

        adapter = new PostAdapter(getApplicationContext());
        favAdapter =new FavPostAdapter(getApplicationContext());
        postsList = new ArrayList<>();
        db = PostDB.getInstance(getApplicationContext());
        binding.FavOpenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FavPostsActivity.class);
                startActivity(intent);
            }
        });
        Single<Response> posts = PostClient.getINSTANCE().postInterface.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        posts.subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        binding.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess(@NonNull Response response) {
                        postsList = response.getHits();
                        adapter.setPosts(postsList);
                        binding.postRV.setAdapter(adapter);
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Iam Done...",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        favPosts = db.postDao().getAllPosts();
                        favAdapter.setPosts(favPosts);
                        binding.postRV.setAdapter(favAdapter);
                        Toast.makeText(getApplicationContext(),"You Are Offline...",Toast.LENGTH_LONG).show();
                    }
                });
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getTaggedPosts(newText);
                return false;
            }
        });
    }
    private void getTaggedPosts(String query)
    {
        Observable<Response> taggedPosts = PostClient.getINSTANCE().postInterface.getQuerryPosts(query)
                .subscribeOn(Schedulers.io())
                .debounce(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
        taggedPosts.subscribe(new Observer<Response>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Response response) {
                postsList = response.getHits();

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        adapter.setPosts(postsList);
        binding.postRV.setAdapter(adapter);

    }
}