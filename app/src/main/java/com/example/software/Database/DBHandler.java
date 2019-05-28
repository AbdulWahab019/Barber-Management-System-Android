package com.example.software.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;

import com.example.software.Bill.AddBill;
import com.example.software.R;

import java.io.IOException;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Software";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Employees = "CREATE TABLE Employees(Eid INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                  "Name varchar, " +
                                                  "FatherName varchar, " +
                                                  "Phone varchar, " +
                                                  "CNIC varchar, " +
                                                  "Address varchar);";

        String Bills = "CREATE TABLE Bills(Bid INTEGER PRIMARY KEY AUTOINCREMENT," +
                                          "price INTEGER," +
                                          "date varchar," +
                                          "time varchar," +
                                          "EmpID INTEGER," +
                                          "FOREIGN KEY (EmpID) REFERENCES Employees(Eid) );";

        db.execSQL(Employees);
        db.execSQL(Bills);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS EMPLOYEES");
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS Bills");
        onCreate(db);
    }

    //Add an Employee
    public void addEmployee(Employees emp){
        ContentValues values = new ContentValues();
        values.put("Name", emp.getName());
        values.put("FatherName", emp.getFatherName());
        values.put("Phone", emp.getPhone());
        values.put("CNIC", emp.getCNIC());
        values.put("Address", emp.getAddress());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("Employees",null, values);
        db.close();
        Log.e("myTag", "Employee Added");
    }

    //Delete an Employee
    public void deleteEmployee(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Employees  WHERE  Eid  =\" " + id + " \"; ");
        Log.e("myTag", "Employee Deleted");
        db.execSQL("DELETE FROM Bills WHERE EmpID = " + id + ";");
        Log.e("myTag","Employee Deleted with its all entries.");
    }

    //View Employees
    public Cursor viewEmployees(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Employees",null);
        return cursor;
    }

    //get Employee ID
    public int getID(String name){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Eid from Employees where name = '" + name + "';", null);
        cursor.moveToNext();
        int ID = cursor.getInt(0);
        return ID;
    }

    //get Employee Name
    public String getName(String ID){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name from Employees where Eid =" + ID + ";",null);
        cursor.moveToNext();
        return cursor.getString(0);
    }

    //Add Bill
    public void addBill(Bills bill) throws IOException{
        ContentValues values = new ContentValues();
        values.put("price", bill.getPrice());
        values.put("date",  bill.getDate());
        values.put("time",  bill.getTime());
        values.put("EmpID", bill.get_Eid());

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.insert("Bills",null,values);
            db.close();
            Log.e("myTag","Bill Added");
        }
        catch (Exception e){
            AddBill add = new AddBill();
            add.showError();
        }
    }

    //Delete a Bill
    public void deleteBill(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Bills  WHERE  Bid  = " + id + ";");
        Log.e("myTag", "Bill Deleted.");
    }

    //View Bills
    public Cursor viewBills(String date){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Bills;",null);
        Log.e("myTag","select * from Bills where date = '" + date + "';");
        return cursor;
    }

    //View Bills by Date
    public Cursor viewBillsByDate(String date){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Bills where date like '" + date + "';",null);
        Log.e("myTag","select * from Bills where date = '" + date + "';");
        return cursor;
    }

    //View Employees Stipend
    public Cursor viewStipendByDate(String date){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Employees.Name,sum(price)/2 from Bills " +
                                         "INNER JOIN Employees " +
                                         "ON Bills.EmpID = Employees.Eid " +
                                         "where date like '" + date + "' " +
                                         "GROUP BY EmpID;",null);
        return cursor;
    }

    //Sales Calculator
    public String Sales(String date){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(price) from Bills where date like '" + date + "';", null);
        cursor.moveToNext();
        return cursor.getString(0);
    }
}
