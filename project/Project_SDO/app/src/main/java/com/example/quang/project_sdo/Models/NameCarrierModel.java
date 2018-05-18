package com.example.quang.project_sdo.Models;

/**
 * Created by Quang on 5/19/2018.
 */

public class NameCarrierModel {
    public String namecarrier;
    public int cmnd;
    public NameCarrierModel() {
    }

    public NameCarrierModel(String namecarrier,int cmnd) {
        this.namecarrier = namecarrier;
    }

    public String getNamecarrier() {
        return namecarrier;
    }

    public void setNamecarrier(String namecarrier) {
        this.namecarrier = namecarrier;
    }

    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }
}
