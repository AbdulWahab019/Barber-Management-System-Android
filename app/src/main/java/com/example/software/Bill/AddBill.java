package com.example.software.Bill;

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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.software.Database.Bills;
import com.example.software.Database.DBHandler;
import com.example.software.Fragments.DatePickerFragment;
import com.example.software.MainActivity;
import com.example.software.R;
import com.example.software.Fragments.TimePickerFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Types.INTEGER;

public class AddBill extends AppCompatActivity {

    DBHandler dbHandler;
    Bills addBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        addItemsOnSpinner2();

        dbHandler = new DBHandler(this,null,null,1);

        FloatingActionButton back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddBill.this, MainActivity.class));
            }
        });

        FloatingActionButton Done = findViewById(R.id.Done);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AddBill();
            startActivity(new Intent(AddBill.this, MainActivity.class));
            }
        });
    }

    private void AddBill(){
        EditText price = findViewById(R.id.price);
        TextView date = findViewById(R.id.date);
        TextView time = findViewById(R.id.time);
        Spinner Worker = findViewById(R.id.Worker);
        dbHandler = new DBHandler(this,null,null,1);

        int empID = dbHandler.getID(Worker.getSelectedItem().toString());
        String dprice = price.getText().toString();
        String ddate = date.getText().toString();
        String dtime = time.getText().toString();

        addBill = new Bills(dprice,ddate,dtime,empID);
        try {
            dbHandler.addBill(addBill);
        }
        catch (Exception e){
            showError();
        }
    }

    private void addItemsOnSpinner2() {
        Spinner spinner2 = findViewById(R.id.Worker);
        dbHandler = new DBHandler(this,null,null,1);

        List<String> list = new ArrayList<>();
        Cursor cursor = dbHandler.viewEmployees();

        while (cursor.moveToNext()){
            list.add(cursor.getString(1));
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(dataAdapter);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showError(){
        Snackbar sn = Snackbar.make(findViewById(R.id.price) , "Bill not added due to an error.",3);
        sn.show();
    }
}
