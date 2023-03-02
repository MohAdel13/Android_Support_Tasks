package com.example.roomdatabase_task;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.roomdatabase_task.databinding.ActivityNewCarBinding;
import com.example.roomdatabase_task.pojo.CarDB;
import com.example.roomdatabase_task.pojo.CarModel;

public class NewCarActivity extends AppCompatActivity {
    ActivityNewCarBinding binding;
    CarDB db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityNewCarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = CarDB.getInstance(NewCarActivity.this);
        binding.backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        binding.saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                String type;
                String model;
                String color;
                CarModel car;
                float price = 0;
                if(binding.typeET.getText().toString().equals("")||binding.modelET.getText().toString().equals("")
                ||binding.colorET.getText().toString().equals("")||binding.priceET.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Please Enter The Missing Information!",Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    try {
                            price = Float.parseFloat(binding.priceET.getText().toString());
                            flag = true;
                    }
                    catch(Exception e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Enter A Valid Price!",Toast.LENGTH_LONG);
                        toast.show();
                    }
                    if(flag)
                    {
                        type = binding.typeET.getText().toString();
                        model = binding.modelET.getText().toString();
                        color = binding.colorET.getText().toString();
                        car = new CarModel();
                        car.type = type;
                        car.model = model;
                        car.color = color;
                        car.price = price;
                        new getAsync().execute(car);
                        Intent intent = new Intent(binding.saveBTN.getContext(),MainActivity.class);
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
            db.carDao().insertCar(carModels[0]);
            return null;
        }
    }
}