package com.example.roomdatabase_task;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase_task.databinding.CarItemBinding;
import com.example.roomdatabase_task.pojo.CarDB;
import com.example.roomdatabase_task.pojo.CarModel;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{
    List<CarModel>cars = new ArrayList<>();
    CarItemBinding binding;
    CarDB db;

    View itemView;
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CarItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        itemView = binding.getRoot();
        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.holderBinding.typeTV.setText(cars.get(position).type);
        holder.holderBinding.modelTV.setText(cars.get(position).model);
        holder.holderBinding.colorTV.setText(cars.get(position).color);
        holder.holderBinding.priceTV.setText(String.valueOf(cars.get(position).price));
    }
    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void setCars(List<CarModel>cars)
    {
        this.cars=cars;
    }

    public List<CarModel> getCars() {
        return cars;
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        CarItemBinding holderBinding;
        public CarViewHolder(@NonNull CarItemBinding binding) {
            super(binding.getRoot());
            db = CarDB.getInstance(binding.getRoot().getContext());
            holderBinding = binding;
            holderBinding.deleteBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CarModel car = cars.get(getAdapterPosition());
                    new getAsync().execute(car);
                    Intent intent = new Intent(v.getContext(),MainActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditCarActivity.cars = cars;
                    Intent intent = new Intent(v.getContext(),EditCarActivity.class);
                    intent.putExtra("carId",getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    private class getAsync extends AsyncTask<CarModel,Void,Void>
    {

        @Override
        protected Void doInBackground(CarModel... carModels) {
            db.carDao().deleteCar(carModels[0]);
            return null;
        }
    }
}
