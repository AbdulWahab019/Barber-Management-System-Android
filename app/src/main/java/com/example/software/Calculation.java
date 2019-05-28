package com.example.software;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.software.Bill.AddBill;
import com.example.software.Database.DBHandler;
import com.example.software.Fragments.DatePickerFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Calculation extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        FloatingActionButton Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calculation.this, MainActivity.class));
            }
        });

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void ViewStipend(View view){
        dbHandler = new DBHandler(this,null,null,1);

        ListView listView = (ListView) findViewById(R.id.listView);
        TextView date = findViewById(R.id.date);
        TextView Sale = findViewById(R.id.TodaySales);
        String Date = date.getText().toString();

        String i = dbHandler.Sales(Date);
        Sale.setText(i);

        Cursor cursor = dbHandler.viewStipendByDate(Date);
        ArrayList<String> data = new ArrayList<>();
        String p;
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data Found.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {

                p = cursor.getString(0) + "         " + cursor.getString(1);
                data.add(p);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
