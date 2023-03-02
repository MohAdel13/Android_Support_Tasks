package com.example.roomdatabase_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.roomdatabase_task.databinding.ActivityEditCarBinding;
import com.example.roomdatabase_task.pojo.CarDB;
import com.example.roomdatabase_task.pojo.CarModel;

import java.util.List;

public class EditCarActivity extends AppCompatActivity {
    ActivityEditCarBinding binding;
    int carId;
    CarDB db;
    static List<CarModel> cars;
    CarModel car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityEditCarBinding.inflate(LayoutInflater.from(getApplicationContext()));
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        carId = intent.getIntExtra("carId", 0);
        db = CarDB.getInstance(getApplicationContext());
        car = cars.get(carId);
        binding.saveBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                String type;
                String model;
                String color;
                CarModel car;
                float price = 0;
                if (binding.typeET2.getText().toString().equals("") || binding.modelET2.getText().toString().equals("")
                        || binding.colorET2.getText().toString().equals("") || binding.priceET2.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter The Missing Information!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    try {
                        price = Float.parseFloat(binding.priceET2.getText().toString());
                        flag = true;
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Enter A Valid Price!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    if (flag) {
                        type = binding.typeET2.getText().toString();
                        model = binding.modelET2.getText().toString();
                        color = binding.colorET2.getText().toString();
                        car = new CarModel();
                        car.type = type;
                        car.model = model;
                        car.color = color;
                        car.price = price;
                        new EditCarActivity.getAsync().execute(car);
                        Intent intent = new Intent(binding.saveBTN2.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private class getAsync extends AsyncTask<CarModel,Void,Void>
    {

        @Override
        protected Void doInBackground(CarModel... carModels) {
            db.carDao().updateCar(car);
            return null;
        }
    }
}