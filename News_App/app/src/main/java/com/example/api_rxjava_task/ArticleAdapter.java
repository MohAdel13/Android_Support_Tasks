package com.example.api_rxjava_task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.api_rxjava_task.databinding.ArticleItemBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{
    ArticleItemBinding binding;
    List<ArticleModel> articles;
    Context context;
    ArticleDB db;

    public ArticleAdapter(Context context)
    {
        this.context = context;
        articles = new ArrayList<>();
        db = ArticleDB.getInstance(this.context);
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        Glide.with(context).load(articles.get(position).urlToImage).into(holder.binding.articleIV);
        holder.binding.descriptionTV.setText(articles.get(position).description);
        holder.binding.titleTV.setText(articles.get(position).title);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        ArticleItemBinding binding;
        public ArticleViewHolder(@NonNull ArticleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.ArticleCV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArticleViewActivity.setArticle(articles.get(getAdapterPosition()));
                    Intent intent = new Intent(context,ArticleViewActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            this.binding.saveBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Completable completable = db.articleDao().insertArticle(articles.get(getAdapterPosition()))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    completable.subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            v.setBackgroundResource(R.drawable.baseline_bookmark_added_24);
                            Toast.makeText(context,"Inserted Successfully...",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                        }
                    });
                }
            });
        }
    }
}
