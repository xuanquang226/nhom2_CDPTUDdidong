package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/24/2018.
 */

public class ListDrugModel {
    public String tenthuoc, gia, linkhinh, tenshop;

    public ListDrugModel() {
    }

    public ListDrugModel(String tenthuoc, String gia, String linkhinh, String tenshop) {
        this.tenthuoc = tenthuoc;
        this.gia = gia;
        this.linkhinh = linkhinh;
        this.tenshop = tenshop;
    }

    public String getTenthuoc() {
        return tenthuoc;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getLinkhinh() {
        return linkhinh;
    }

    public void setLinkhinh(String linkhinh) {
        this.linkhinh = linkhinh;
    }

    public String getTenshop() {
        return tenshop;
    }

    public void setTenshop(String tenshop) {
        this.tenshop = tenshop;
    }
}

