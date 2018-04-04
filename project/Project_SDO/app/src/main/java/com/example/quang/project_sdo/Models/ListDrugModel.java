package com.example.quang.project_sdo.Models;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by ITLAB on 4/4/2018.
 */

public class ListDrugModel {
    public String nameDrug, nameStore;
    public String priceDrug;
    public Integer imageDrug;

    public ListDrugModel(String nameDrug, String nameStore, String priceDrug, Integer imageDrug, String imagePrice) {
        this.nameDrug = nameDrug;
        this.nameStore = nameStore;
        this.priceDrug = priceDrug;
        this.imageDrug = imageDrug;

    }

    public String getNameDrug() {
        return nameDrug;
    }

    public void setNameDrug(String nameDrug) {
        this.nameDrug = nameDrug;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getPriceDrug() {
        return priceDrug;
    }

    public void setPriceDrug(String priceDrug) {
        this.priceDrug = priceDrug;
    }

    public Integer getImageDrug() {
        return imageDrug;
    }

    public void setImageDrug(Integer imageDrug) {
        this.imageDrug = imageDrug;
    }
}
