package com.example.quang.project_sdo.Models;

public class OrderModel {

    public String ten, hinh;
    public int gia, soLuong;



    public OrderModel() {
    }

    public OrderModel(String ten, String hinh, int gia, int soLuong) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.soLuong = soLuong;
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


}
