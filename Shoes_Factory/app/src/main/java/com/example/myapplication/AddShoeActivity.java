package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class AddShoeActivity extends AppCompatActivity {
    ShoeDatabaseHelper db=new ShoeDatabaseHelper(this);
    TextView add_brandET;
    TextView add_sizeET;
    TextView add_modelET;
    TextView add_descriptionET;
    Button addBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_shoe);
        add_brandET = findViewById(R.id.add_brandET);
        add_sizeET = findViewById(R.id.add_SizeET);
        add_descriptionET = findViewById(R.id.add_descriptionET);
        add_modelET = findViewById(R.id.add_modelET);
        addBTN = findViewById(R.id.addBTN);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = add_brandET.getText().toString();
                String model = add_modelET.getText().toString();
                String description = add_descriptionET.getText().toString();
                int size = Integer.parseInt(add_sizeET.getText().toString());
                ShoeModel s = new ShoeModel(brand,size,model,description);
                db.insertShoe(s);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}