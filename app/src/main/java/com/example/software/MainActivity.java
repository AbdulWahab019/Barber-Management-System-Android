package com.example.software;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.software.Bill.AddBill;
import com.example.software.Bill.DeleteBill;
import com.example.software.Bill.ViewBill;
import com.example.software.Database.DBHandler;
import com.example.software.Worker.Workers_Main;

public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this, null,null,1);
    }

    public void AddBill(View view){
        startActivity(new Intent(MainActivity.this, AddBill.class));
    }

    public void ViewBill(View view){ startActivity(new Intent(MainActivity.this, ViewBill.class)); }

    public void DeleteBill(View view){ startActivity(new Intent(MainActivity.this, DeleteBill.class)); }

    public void Workers(View view){ startActivity(new Intent(MainActivity.this, Workers_Main.class)); }

    public void Stipend(View view){ startActivity(new Intent(MainActivity.this, Calculation.class)); }

}
