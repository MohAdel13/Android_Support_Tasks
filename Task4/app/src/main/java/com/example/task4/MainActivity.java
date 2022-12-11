package com.example.task4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView taskRV;
    TaskAdapter taskAdapter;
    List<String> tasks;
    FloatingActionButton addBTN;
    EditText taskET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tasks = new ArrayList<>();
        tasks.add("Support Task");
        tasks.add("OS Task");
        addBTN = findViewById(R.id.addBTN);
        taskRV = findViewById(R.id.taskRV);
        taskAdapter = new TaskAdapter();
        taskAdapter.setTasks(tasks);
        taskRV.setAdapter(taskAdapter);
        taskRV.setLayoutManager(new LinearLayoutManager(this));
        taskET = findViewById(R.id.taskET);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTask = taskET.getText().toString();
                if(newTask.equals(""))
                {
                    Toast.makeText(MainActivity.this, "You Didn't Write A Task To Add", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    tasks.add(newTask);
                    taskAdapter.setTasks(tasks);
                    taskRV.setAdapter(taskAdapter);
                }
            }
        });
    }
}