package com.example.software.Database;

import java.sql.Date;
import java.sql.Time;

public class Bills {

    private String Bid;
    private String price;
    private String date;
    private String time;
    private int _Eid;

    public Bills(){

    }

    public Bills(String price, String date, String time,int employee){
        this.price = price;
        this.date = date;
        this.time = time;
        this._Eid = employee;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int get_Eid() {
        return _Eid;
    }

    public void set_Eid(int _Eid) {
        this._Eid = _Eid;
    }
}
