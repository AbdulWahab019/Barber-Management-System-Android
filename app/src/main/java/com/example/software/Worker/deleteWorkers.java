package com.example.software.Worker;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.software.Database.DBHandler;
import com.example.software.R;

public class deleteWorkers extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_workers);

        FloatingActionButton back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(deleteWorkers.this, Workers_Main.class));
            }
        });
    }

    public void Delete(View view){

        EditText EID = findViewById(R.id.ID);
        String EmpID = EID.getText().toString();
        dbHandler = new DBHandler(this,null,null,1);
        dbHandler.deleteEmployee(EmpID);

        startActivity(new Intent(deleteWorkers.this, Workers_Main.class));
    }
}
