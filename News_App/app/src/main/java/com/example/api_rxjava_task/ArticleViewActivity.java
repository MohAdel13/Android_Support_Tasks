package com.example.api_rxjava_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.api_rxjava_task.databinding.ActivityArticleViewBinding;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ArticleViewActivity extends AppCompatActivity {
    private static ArticleModel article;
    ActivityArticleViewBinding binding;
    ArticleDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = ArticleDB.getInstance(getApplicationContext());
        binding.contentTV.setText(article.content);
        binding.titleTV2.setText(article.title);
        Glide.with(getApplicationContext()).load(article.urlToImage).into(binding.imageView);
        binding.saveBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable completable = db.articleDao().insertArticle(article)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                completable.subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(),"Inserted Successfully......",Toast.LENGTH_LONG).show();
                        v.setBackgroundResource(R.drawable.baseline_bookmark_added_24);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
            }
        });
    }

    public static void setArticle(ArticleModel article) {
        ArticleViewActivity.article = article;
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