package com.example.software.Worker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.software.Database.DBHandler;
import com.example.software.Database.Employees;
import com.example.software.R;

public class addWorkers extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workers);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton Back = findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addWorkers.this, Workers_Main.class));
            }
        });
    }

    public void Add(View view){

        EditText Name = findViewById(R.id.name);
        EditText FatherName = findViewById(R.id.FatherName);
        EditText Phone = findViewById(R.id.phone);
        EditText CNIC = findViewById(R.id.CNIC);
        EditText Address = findViewById(R.id.Address);

        Employees emp = new Employees(Name.getText().toString() ,FatherName.getText().toString(), Phone.getText().toString(), CNIC.getText().toString(), Address.getText().toString());
        dbHandler = new DBHandler(this, null, null,1);
        dbHandler.addEmployee(emp);

        startActivity(new Intent(addWorkers.this, Workers_Main.class));
    }
}

