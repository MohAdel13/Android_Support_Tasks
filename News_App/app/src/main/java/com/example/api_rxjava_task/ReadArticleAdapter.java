package com.example.api_rxjava_task;

import android.content.Context;
import android.content.Intent;
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

public class ReadArticleAdapter extends RecyclerView.Adapter<ReadArticleAdapter.ReadArticleViewHolder>{
    ArticleDB db;
    ArticleItemBinding binding;
    Context context;
    List<ArticleModel> articlesList;

    public ReadArticleAdapter(Context context) {
        this.context = context;
        db = ArticleDB.getInstance(this.context);
        articlesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReadArticleAdapter.ReadArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ReadArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadArticleAdapter.ReadArticleViewHolder holder, int position) {
        Glide.with(context).load(articlesList.get(position).urlToImage).into(holder.binding.articleIV);
        holder.binding.descriptionTV.setText(articlesList.get(position).description);
        holder.binding.titleTV.setText(articlesList.get(position).title);
        holder.binding.saveBTN.setImageResource(R.drawable.baseline_bookmark_added_24);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articlesList = articles;
    }

    public class ReadArticleViewHolder extends RecyclerView.ViewHolder {
        ArticleItemBinding binding;
        public ReadArticleViewHolder(@NonNull ArticleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.ArticleCV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArticleViewActivity.setArticle(articlesList.get(getAdapterPosition()));
                    Intent intent = new Intent(context,ArticleViewActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            this.binding.saveBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Completable completable = db.articleDao().deleteArticle(articlesList.get(getAdapterPosition()))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    completable.subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            v.setBackgroundResource(R.drawable.baseline_bookmark_add_24);
                            Toast.makeText(context,"Deleted Successfully...",Toast.LENGTH_LONG).show();
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
