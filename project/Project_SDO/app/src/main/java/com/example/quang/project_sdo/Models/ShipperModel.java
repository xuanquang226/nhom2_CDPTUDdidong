package com.example.quang.project_sdo.Models;

public class ShipperModel {
    String email, pass, address, phone, cmnd, vehicle, airline;

    public ShipperModel(String email, String pass, String address, String phone) {
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.phone = phone;
        this.cmnd = cmnd;
        this.vehicle = vehicle;
        this.airline = airline;
    }

    public ShipperModel() {
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
