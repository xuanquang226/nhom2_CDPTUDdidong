package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/5/2018.
 */

public class ListDrugForHomeModel {
    public String drugName, drugDescription, drugPostDate;
    public Integer drugImage;

    public ListDrugForHomeModel(String drugName, String drugDescription, String drugPostDate, Integer drugImage) {
        this.drugName = drugName;
        this.drugDescription = drugDescription;
        this.drugPostDate = drugPostDate;
        this.drugImage = drugImage;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugDescription() {
        return drugDescription;
    }

    public void setDrugDescription(String drugDescription) {
        this.drugDescription = drugDescription;
    }

    public String getDrugPostDate() {
        return drugPostDate;
    }

    public void setDrugPostDate(String drugPostDate) {
        this.drugPostDate = drugPostDate;
    }

    public Integer getDrugImage() {
        return drugImage;
    }

    public void setDrugImage(Integer drugImage) {
        this.drugImage = drugImage;
    }
}
