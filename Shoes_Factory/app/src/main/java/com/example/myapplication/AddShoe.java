package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class AddShoe extends AppCompatActivity {
    ShoeDatabaseHelper db=new ShoeDatabaseHelper(this);
    TextView add_brandTV;
    TextView add_sizeTV;
    TextView add_modelTV;
    TextView add_descriptionTV;
    Button addBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_shoe);
        add_brandTV = findViewById(R.id.add_brandTV);
        add_sizeTV = findViewById(R.id.add_SizeTV);
        add_descriptionTV = findViewById(R.id.add_descriptionTV);
        add_modelTV = findViewById(R.id.add_modelTV);
        addBTN = findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = add_brandTV.getText().toString();
                String model = add_modelTV.getText().toString();
                String description = add_descriptionTV.getText().toString();
                int size = Integer.parseInt(add_sizeTV.getText().toString());
                ShoeModel s = new ShoeModel(brand,size,model,description);
                db.insertShoe(s);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}