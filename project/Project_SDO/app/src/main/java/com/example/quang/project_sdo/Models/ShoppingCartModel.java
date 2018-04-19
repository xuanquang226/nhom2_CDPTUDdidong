package com.example.quang.project_sdo.Models;

public class ShoppingCartModel {
    public String drugName, drugTextAmount;
    public Integer drugImage,drugPrice ;

    public ShoppingCartModel() {
    }

    public ShoppingCartModel(String drugName,   Integer drugPrice,Integer drugImage, String drugTextAmount) {
        this.drugName = drugName;
        this.drugPrice = drugPrice;
        this.drugImage = drugImage;
        this.drugTextAmount = drugTextAmount;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugTextAmount() {
        return drugTextAmount;
    }

    public void setDrugTextAmount(String drugTextAmount) {
        this.drugTextAmount = drugTextAmount;
    }

    public Integer getDrugImage() {
        return drugImage;
    }

    public void setDrugImage(Integer drugImage) {
        this.drugImage = drugImage;
    }

    public Integer getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(Integer drugPrice) {
        this.drugPrice = drugPrice;
    }


}
