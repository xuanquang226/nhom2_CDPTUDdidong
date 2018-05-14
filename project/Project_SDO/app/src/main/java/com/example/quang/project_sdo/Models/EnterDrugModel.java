package com.example.quang.project_sdo.Models;

import java.util.Date;

/**
 * Created by Trang on 4/24/2018.
 */

public class EnterDrugModel {
    public String tenthuoc, congdung, gia, nguongoc, mota, soluong, linkhinh, tenshop,id,idShop;
    public long date;

    public EnterDrugModel() {
    }

    public EnterDrugModel(String tenthuoc, String congdung, String gia, String nguongoc, String mota, String soluong, String linkhinh, String tenshop,String id,String idShop) {
        this.tenthuoc = tenthuoc;
        this.congdung = congdung;
        this.gia = gia;
        this.nguongoc = nguongoc;
        this.mota = mota;
        this.soluong = soluong;
        this.linkhinh = linkhinh;
        this.tenshop = tenshop;
        this.id = id;
        this.idShop = idShop;
        this.date = new Date().getTime();
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}

