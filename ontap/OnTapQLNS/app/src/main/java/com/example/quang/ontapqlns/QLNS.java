package com.example.quang.ontapqlns;

/**
 * Created by Quang on 1/4/2018.
 */

public class QLNS {
    private String hoten;
    private int bangcap;
    private String sothich;
    private boolean isChecked = false;

    public QLNS(String hoten, int bangcap, String sothich) {
        this.hoten = hoten;
        this.bangcap = bangcap;
        this.sothich = sothich;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getBangcap() {
        return bangcap;
    }

    public void setBangcap(int bangcap) {
        this.bangcap = bangcap;
    }

    public String getSothich() {
        return sothich;
    }

    public void setSothich(String sothich) {
        this.sothich = sothich;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
