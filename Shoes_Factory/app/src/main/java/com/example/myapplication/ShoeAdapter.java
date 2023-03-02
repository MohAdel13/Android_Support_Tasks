package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import java.util.List;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>{
    List<ShoeModel> shoes = new ArrayList<>();
    @NonNull
    @Override
    public ShoeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe_item,parent,false);
        return new ShoeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeViewHolder holder, int position) {
        holder.shoeModelTV.setText(shoes.get(position).model);
        holder.shoeBrandTV.setText(shoes.get(position).brand);
        holder.shoeDescriptionTV.setText(shoes.get(position).description);
        holder.shoeSizeTV.setText(Integer.toString(shoes.get(position).size));
    }

    @Override
    public int getItemCount() {
        return shoes.size();
    }

    public void setShoes(List<ShoeModel> s)
    {
        this.shoes = s;
    }


    public static class ShoeViewHolder extends RecyclerView.ViewHolder {
        TextView shoeModelTV;
        TextView shoeBrandTV;
        TextView shoeSizeTV;
        TextView shoeDescriptionTV;

        public ShoeViewHolder(@NonNull View itemView) {
            super(itemView);
            shoeModelTV = itemView.findViewById(R.id.shoeModelTV);
            shoeBrandTV = itemView.findViewById(R.id.shoeBrandTV);
            shoeSizeTV = itemView.findViewById(R.id.shoeSizeTV);
            shoeDescriptionTV = itemView.findViewById(R.id.shoeDescriptionTV);
        }
    }
}
