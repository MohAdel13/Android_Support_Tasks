package com.example.roomdatabase_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.roomdatabase_task.databinding.ActivityMainBinding;
import com.example.roomdatabase_task.pojo.CarDB;
import com.example.roomdatabase_task.pojo.CarModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<CarModel> cars;
    ActivityMainBinding binding;
    CarAdapter adapter;
    CarDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cars=new ArrayList<>();
        adapter=new CarAdapter();
        db = CarDB.getInstance(MainActivity.this);
        new getAsync().execute();
        binding.carRV.setLayoutManager(new LinearLayoutManager(this));
        binding.addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),NewCarActivity.class);
                startActivity(intent);
            }
        });
    }
    private class getAsync extends AsyncTask<Void,Void,List<CarModel>>
    {

        @Override
        protected List<CarModel> doInBackground(Void... voids) {
            cars = db.carDao().getAllCars();
            return cars;
        }

        @Override
        protected void onPostExecute(List<CarModel> carModels) {
            super.onPostExecute(carModels);
            adapter.setCars(cars);
            binding.carRV.setAdapter(adapter);
        }
    }
}