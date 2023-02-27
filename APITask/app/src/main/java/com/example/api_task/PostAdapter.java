package com.example.api_task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.api_task.databinding.PostItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    List<PostModel> posts;
    Context context;
    PostItemBinding binding;
    PostDB db;

    public PostAdapter(Context context) {
        this.context = context;
        this.posts =new ArrayList<>();
        db = PostDB.getInstance(context);
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PostItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        Glide.with(context).load(posts.get(position).userImageURL).into(binding.userIMG);
        Glide.with(context).load(posts.get(position).largeImageURL).into(binding.postIMG);
        binding.commentsTV.setText(Integer.toString(posts.get(position).comments));
        binding.likesTV.setText(Integer.toString(posts.get(position).likes));
        binding.downloadsTV.setText(Integer.toString(posts.get(position).downloads));
        binding.favBTN.setImageResource(R.drawable.baseline_favorite_border_24);
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
            this.binding.favBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getAsync().execute(posts.get(getAdapterPosition()));
                    v.setBackgroundResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(context,"Inserted Successfully...",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private class getAsync extends AsyncTask<PostModel,Void,Void>
    {

        @Override
        protected Void doInBackground(PostModel... postModels) {
            db.postDao().insertPost(postModels[0]);
            return null;
        }
    }
}
