package com.example.api_rxjava_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.api_rxjava_task.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    ArticleAdapter adapter;
    List<ArticleModel> articlesList;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String TAG ="Tag";
        adapter = new ArticleAdapter(getApplicationContext());
        articlesList=new ArrayList<>();
        Single<Response> articles = ArticleClient.getInstance().articleInterface.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        articles.subscribe(new SingleObserver<Response>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                binding.search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(@NonNull Response response) {
                articlesList = response.getArticles();
                Log.d(TAG, "onSuccess: "+response.status);
                adapter.setArticles(articlesList);
                binding.ArticleRV.setAdapter(adapter);
                binding.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();            }
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
        Observable<Response> taggedPosts = ArticleClient.getInstance().articleInterface.getQueryArticles(query)
                .subscribeOn(Schedulers.io())
                .debounce(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
        taggedPosts.subscribe(new Observer<Response>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Response response) {
                articlesList = response.getArticles();

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        adapter.setArticles(articlesList);
        binding.ArticleRV.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@androidx.annotation.NonNull MenuItem item) {
        Intent intent =new Intent(getApplicationContext(),ReadArticleActivity.class);
        startActivity(intent);
        return true;
    }
}