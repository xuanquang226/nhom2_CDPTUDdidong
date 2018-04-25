package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/24/2018.
 */

public class EnterDrugModel {
    String tenthuoc,congdung,gia,nguongoc,mota;
    int soluong;

    public EnterDrugModel(String tenthuoc, String congdung, String gia, String nguongoc, String mota,int soluong) {
        this.tenthuoc = tenthuoc;
        this.congdung = congdung;
        this.gia = gia;
        this.nguongoc = nguongoc;
        this.mota = mota;
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTenthuoc() {
        return tenthuoc;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public String getCongdung() {
        return congdung;
    }

    public void setCongdung(String congdung) {
        this.congdung = congdung;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getNguongoc() {
        return nguongoc;
    }

    public void setNguongoc(String nguongoc) {
        this.nguongoc = nguongoc;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
