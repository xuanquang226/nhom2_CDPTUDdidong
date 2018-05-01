package com.example.quang.project_sdo.Models;

public class ShipperModel {
    public String email, pass, address, phone, cmnd, vehicle,accountType,id,linkhinh;

    public ShipperModel(String email, String pass, String address, String phone,String cmnd,String vehicle,String accountType,String id,String linkhinh) {
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
        this.cmnd = cmnd;
        this.vehicle = vehicle;
        this.accountType = accountType;
        this.id = id;
        this.linkhinh = linkhinh;
    }

    public ShipperModel() {
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

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

}
