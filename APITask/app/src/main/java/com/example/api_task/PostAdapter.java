package com.example.api_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_task.databinding.PostItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    List<PostModel> posts;
    Context context;
    PostItemBinding binding;

    public PostAdapter(Context context) {
        this.context = context;
        this.posts =new ArrayList<>();
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PostItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        holder.binding.titleTV.setText(posts.get(position).title);
        holder.binding.bodyTV.setText(posts.get(position).body);
        holder.binding.idTV.setText("ID: "+Integer.toString(posts.get(position).id));
        holder.binding.userIdTV.setText("UserID: "+Integer.toString(posts.get(position).userId));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        PostItemBinding binding;
        public PostViewHolder(@NonNull PostItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
