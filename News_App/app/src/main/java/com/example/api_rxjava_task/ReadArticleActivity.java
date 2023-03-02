package com.example.api_rxjava_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.api_rxjava_task.databinding.ActivityReadArticleBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReadArticleActivity extends AppCompatActivity {
    ArticleDB db;
    ActivityReadArticleBinding binding;
    List<ArticleModel> articlesList;
    ReadArticleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        articlesList =new ArrayList<>();
        db = ArticleDB.getInstance(getApplicationContext());
        adapter = new ReadArticleAdapter(getApplicationContext());
        Single<List<ArticleModel>> articles = db.articleDao().getAllArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        articles.subscribe(new SingleObserver<List<ArticleModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<ArticleModel> articleModels) {
                binding.progressBar.setVisibility(View.INVISIBLE);
                articlesList = articleModels;
                adapter.setArticles(articlesList);
                binding.ArticleRV2.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"Loaded Successfully....",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }
        });
    }
}