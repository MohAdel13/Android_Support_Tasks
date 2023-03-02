package com.example.task3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Button logInBTN,signUpBTN;
        EditText emailEDT,passEDT;
        emailEDT=findViewById(R.id.email_EDT);
        passEDT=findViewById(R.id.pass_EDT);
        logInBTN=findViewById(R.id.signUp_BTN);
        signUpBTN=findViewById(R.id.logIn_BTN);
        logInBTN.setOnClickListener(view -> {
            if(emailEDT.getText().toString().equals("")||passEDT.getText().toString().equals(""))
            {
                Toast.makeText(MainActivity.this, "You Must Enter Your Email And Password!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent i = new Intent(view.getContext(),Welcome.class);
                i.putExtra("Email",emailEDT.getText().toString());
                startActivity(i);
                Toast.makeText(MainActivity.this, "Welcome "+emailEDT.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        signUpBTN.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(),SignUp.class);
            startActivity(i);
        });
    }
}