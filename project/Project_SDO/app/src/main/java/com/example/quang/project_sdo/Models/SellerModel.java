package com.example.quang.project_sdo.Models;

public class SellerModel {
    String email, pass, address, phone, cmnd, drugstore;

    public SellerModel(String email, String pass, String address, String phone) {
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
        this.cmnd = cmnd;
        this.drugstore = drugstore;
    }

    public SellerModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setDrugstore(String drugstore) {
        this.drugstore = drugstore;
    }

    public String getDrugstore() {
        return drugstore;
    }
}
