package com.example.quang.project_sdo.Models;

public class UsersModel {
    String email, pass, address, phone;

    public UsersModel(String email, String pass, String address, String phone) {
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
    }

    public UsersModel() {
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

}
