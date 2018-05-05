package com.example.quang.project_sdo.Models;

public class SaleStatisticModel {
    public String drugName,drugImage, drugPrice, drugDate, drugAmount;

    public SaleStatisticModel(String drugName, String drugImage, String drugPrice, String drugDate,String drugAmount) {
        this.drugName = drugName;
        this.drugImage = drugImage;
        this.drugPrice = drugPrice;
        this.drugDate = drugDate;
        this.drugAmount = drugAmount;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugImage() {
        return drugImage;
    }

    public void setDrugImage(String drugImage) {
        this.drugImage = drugImage;
    }

    public String getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(String drugPrice) {
        this.drugPrice = drugPrice;
    }

    public String getDrugDate() {
        return drugDate;
    }

    public void setDrugDate(String drugDate) {
        this.drugDate = drugDate;
    }

    public String getDrugAmount() {
        return drugAmount;
    }

    public void setDrugAmount(String drugAmount) {
        this.drugAmount = drugAmount;
    }
}
