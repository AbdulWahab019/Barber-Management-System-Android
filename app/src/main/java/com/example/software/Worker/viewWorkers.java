package com.example.software.Worker;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.software.Database.DBHandler;
import com.example.software.R;

import java.util.ArrayList;
import java.util.List;

public class viewWorkers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workers);

        FloatingActionButton back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(viewWorkers.this, Workers_Main.class));
            }
        });

        ListView listView = (ListView) findViewById(R.id.ListView);

        DBHandler dbHandler = new DBHandler(this,null,null,1);
        Cursor cursor = dbHandler.viewEmployees();
        ArrayList<String> data = new ArrayList<>();
        String p;
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No Data Found.",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                p = " ID:                         " + cursor.getString(0) + " \n " +
                    "Name:                 " + cursor.getString(1) + " \n " +
                    "Father Name:     " + cursor.getString(2) + " \n " +
                    "Phone:                 " + cursor.getString(3) + " \n " +
                    "CNIC:                   " + cursor.getString(4) + " \n " +
                    "Address:              " + cursor.getString(5);

                data.add(p);
                ListAdapter listAdapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
