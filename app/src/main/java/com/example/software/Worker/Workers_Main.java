package com.example.software.Worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.software.MainActivity;
import com.example.software.R;

public class Workers_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers__main);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Workers_Main.this, MainActivity.class));
            }
        });
    }

    public void ViewWorkers(View view){ startActivity(new Intent(Workers_Main.this, viewWorkers.class)); }
    public void deleteWorkers(View view){ startActivity(new Intent(Workers_Main.this, deleteWorkers.class)); }
    public void addWorkers(View view){ startActivity(new Intent(Workers_Main.this, addWorkers.class)); }

}
