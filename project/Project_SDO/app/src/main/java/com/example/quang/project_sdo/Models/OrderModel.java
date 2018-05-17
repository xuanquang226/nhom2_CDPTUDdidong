package com.example.quang.project_sdo.Models;

import java.util.Date;

public class OrderModel {

    public String ten, hinh;
    public Integer gia, soLuong;
    public long time;
    public boolean isChecked;
    public String key;



    public OrderModel() {
    }

    public OrderModel(String ten, String hinh, Integer gia, Integer soLuong, String key) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.time = new Date().getTime();
        this.isChecked = false;
        this.key = key;
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

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
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
}
