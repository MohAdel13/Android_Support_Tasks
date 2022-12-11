package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        EditText name_EDT,email_EDT,pass_EDT;
        Button signUp_BTN,logIn_BTN;
        name_EDT = findViewById(R.id.name_EDT);
        email_EDT = findViewById(R.id.email_EDT);
        pass_EDT = findViewById(R.id.pass_EDT);
        signUp_BTN = findViewById(R.id.signUp_BTN);
        logIn_BTN = findViewById(R.id.logIn_BTN);
        signUp_BTN.setOnClickListener(view -> {
            if(email_EDT.getText().toString().equals("")||pass_EDT.getText().toString().equals("")||
                    name_EDT.getText().toString().equals(""))
            {
                Toast.makeText(SignUp.this, "You Must Enter All Your Information!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent i = new Intent(view.getContext(),Welcome.class);
                i.putExtra("Email",name_EDT.getText().toString());
                startActivity(i);
                Toast.makeText(SignUp.this, "Welcome "+name_EDT.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        logIn_BTN.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(),MainActivity.class);
            startActivity(i);
        });
    }
}