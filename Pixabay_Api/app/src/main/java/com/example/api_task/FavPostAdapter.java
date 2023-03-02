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
import com.example.api_task.databinding.FavPostItemBinding;

import java.util.ArrayList;
import java.util.List;

public class FavPostAdapter extends RecyclerView.Adapter<FavPostAdapter.FavPostViewHolder>{
    Context context;
    FavPostItemBinding binding;
    List<PostModel> posts;
    PostDB db;

    public FavPostAdapter(Context context)
    {
        this.context = context;
        posts =new ArrayList<>();
        db = PostDB.getInstance(context);
    }

    @NonNull
    @Override
    public FavPostAdapter.FavPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FavPostItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FavPostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavPostAdapter.FavPostViewHolder holder, int position) {
        Glide.with(context).load(posts.get(position).userImageURL).into(binding.userIMG2);
        Glide.with(context).load(posts.get(position).largeImageURL).into(binding.postIMG2);
        binding.commentsTV2.setText(Integer.toString(posts.get(position).comments));
        binding.likesTV2.setText(Integer.toString(posts.get(position).likes));
        binding.downloadsTV2.setText(Integer.toString(posts.get(position).downloads));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<PostModel> posts)
    {
        this.posts = posts;
    }
    public class FavPostViewHolder extends RecyclerView.ViewHolder {
        FavPostItemBinding binding;
        public FavPostViewHolder(@NonNull FavPostItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.favBTN2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new getAsync().execute(posts.get(getAdapterPosition()));
                    v.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                    Toast.makeText(context, "Deleted Successfully...", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private class getAsync extends AsyncTask<PostModel,Void,Void>
    {
        @Override
        protected Void doInBackground(PostModel... postModels) {
            db.postDao().deletePost(postModels[0]);
            return null;
        }
    }
}
