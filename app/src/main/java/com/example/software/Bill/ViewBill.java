package com.example.software.Bill;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.software.Database.DBHandler;
import com.example.software.Fragments.DatePickerFragment;
import com.example.software.MainActivity;
import com.example.software.R;

import java.util.ArrayList;

public class ViewBill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);

        FloatingActionButton back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewBill.this, MainActivity.class));
            }
        });
        viewBills();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void viewBills() {
        ListView listView = (ListView) findViewById(R.id.listView);
        TextView date = (TextView) findViewById(R.id.date);
        String d = date.getText().toString();

        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        Cursor cursor = dbHandler.viewBills(d);
        ArrayList<String> data = new ArrayList<>();
        String p;
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data Found.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String Name = dbHandler.getName(cursor.getString(4));
                p = " ID:                       " + cursor.getString(0) + " \n " +
                        "Price:                 " + cursor.getString(1) + " \n " +
                        "Date:                  " + cursor.getString(2) + " \n " +
                        "Time:                 " + cursor.getString(3) + " \n " +
                        "Employee:          " + Name;
                data.add(p);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(listAdapter);
            }
        }
    }

    public void viewByDate(View v) {
        ListView listView = (ListView) findViewById(R.id.listView);
        TextView date = (TextView) findViewById(R.id.date);
        String d = date.getText().toString();

        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        Cursor cursor = dbHandler.viewBillsByDate(d);
        ArrayList<String> data = new ArrayList<>();
        String p;
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data Found.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                String Name = dbHandler.getName(cursor.getString(4));
                p = " ID:                       " + cursor.getString(0) + " \n " +
                        "Price:                 " + cursor.getString(1) + " \n " +
                        "Date:                  " + cursor.getString(2) + " \n " +
                        "Time:                 " + cursor.getString(3) + " \n " +
                        "Employee:          " + Name;
                data.add(p);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(listAdapter);
            }
        }
    }
}