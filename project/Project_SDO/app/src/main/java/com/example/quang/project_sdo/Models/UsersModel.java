package com.example.quang.project_sdo.Models;

public class UsersModel {
    public String email, pass, address, phone,accountType,id,linkhinh;




    public UsersModel(String email, String pass, String address, String phone,String accountType,String id,String linkhinh) {
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
        this.accountType = accountType;
        this.id = id;
        this.linkhinh = linkhinh;
    }

    public UsersModel() {
    }

    public String getLinkhinh() {
        return linkhinh;
    }

    public void setLinkhinh(String linkhinh) {
        this.linkhinh = linkhinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
