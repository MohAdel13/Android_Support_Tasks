package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ShoeDatabaseHelper db;
    RecyclerView shoeRV;
    ShoeAdapter shoeAdapter;
    List<ShoeModel> shoes;
    Button newBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        db=new ShoeDatabaseHelper(this);
        shoes = new ArrayList<>();
        shoes = db.getShoes();
        shoeRV = findViewById(R.id.shoeRV);
        shoeAdapter = new ShoeAdapter();
        shoeAdapter.setShoes(shoes);
        shoeRV.setAdapter(shoeAdapter);
        shoeRV.setLayoutManager(new LinearLayoutManager(this));
        newBTN=findViewById(R.id.newBTN);
        newBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AddShoe.class);
                startActivity(intent);
            }
        });
    }
}