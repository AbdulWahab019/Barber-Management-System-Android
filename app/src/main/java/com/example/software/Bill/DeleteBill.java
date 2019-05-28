package com.example.software.Bill;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.software.Database.DBHandler;
import com.example.software.MainActivity;
import com.example.software.R;

public class DeleteBill extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bill);

        FloatingActionButton back = findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteBill.this, MainActivity.class));
            }
        });
    }

    public void Delete(View view){
        dbHandler = new DBHandler(this,null,null,1);

        EditText id = findViewById(R.id.BillID);
        String ID = id.getText().toString();
        dbHandler.deleteBill(ID);

        startActivity(new Intent(DeleteBill.this, MainActivity.class));
    }
}
