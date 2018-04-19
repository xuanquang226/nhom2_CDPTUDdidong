package com.example.quang.project_sdo.Models;

/**
 * Created by ITLAB on 4/19/2018.
 */

public class ScheduleModel {
    public String orderShedule, adress;
    public String phoneNumber;

    public ScheduleModel(String orderShedule, String adress, String phoneNumber) {
        this.orderShedule = orderShedule;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public String getOrderShedule() {
        return orderShedule;
    }

    public void setOrderShedule(String orderShedule) {
        this.orderShedule = orderShedule;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
