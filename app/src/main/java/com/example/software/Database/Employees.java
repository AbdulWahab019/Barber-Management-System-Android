package com.example.software.Database;

public class Employees {

    private int _Eid;
    private String Name;
    private String FatherName;
    private String Phone;
    private String CNIC;
    private String Address;

    public Employees(){
    }

    public Employees(String name, String phone) {
        Name = name;
        Phone = phone;
    }

    public Employees(String name, String fatherName, String phone) {
        Name = name;
        FatherName = fatherName;
        Phone = phone;
    }

    public Employees(String name, String fatherName, String phone, String CNIC) {
        Name = name;
        FatherName = fatherName;
        Phone = phone;
        this.CNIC = CNIC;
    }

    public Employees(String name, String fatherName, String phone, String CNIC, String address) {
        Name = name;
        FatherName = fatherName;
        Phone = phone;
        this.CNIC = CNIC;
        Address = address;
    }

    public int get_Eid() {
        return _Eid;
    }

    public void set_Eid(int _Eid) {
        this._Eid = _Eid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
