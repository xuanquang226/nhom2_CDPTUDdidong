package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 5/19/2018.
 */

public class LOrderModel {
    public String tenthuoc;
    public int tongtien;
    public String diachi;
    public String hang;
    public String idUser;

    public LOrderModel() {
    }

    public LOrderModel(String tenthuoc, int tongtien, String diachi, String hang,String idUser) {
        this.tenthuoc = tenthuoc;
        this.tongtien = tongtien;
        this.diachi = diachi;
        this.hang = hang;
        this.idUser = idUser;
    }

    public String getTenthuoc() {
        return tenthuoc;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
