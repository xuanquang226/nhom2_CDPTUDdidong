package com.example.quang.project_sdo.Models;

import java.util.Date;

public class OrderModel {

    public String ten, hinh;
    public int gia, soLuong;
    public long time;
    public boolean isChecked;
    public String key;
    public int giagoc;



    public OrderModel() {
    }

    public OrderModel(String ten, String hinh, int gia, int soLuong, String key,int giagoc) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.time = new Date().getTime();
        this.isChecked = false;
        this.key = key;
        this.giagoc = giagoc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getGiagoc() {
        return giagoc;
    }

    public void setGiagoc(int giagoc) {
        this.giagoc = giagoc;
    }
}
