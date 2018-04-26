package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/24/2018.
 */

public class ListDrugForHomeModel {
    public String drugName,drugImage, drugPost,drugDescription;

    public ListDrugForHomeModel() {
    }

    public ListDrugForHomeModel(String drugName, String drugImage, String drugPost, String drugDescription) {
        this.drugName = drugName;
        this.drugImage = drugImage;
        this.drugPost = drugPost;
        this.drugDescription = drugDescription;
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

    public String getDrugPost() {
        return drugPost;
    }

    public void setDrugPost(String drugPost) {
        this.drugPost = drugPost;
    }

    public String getDrugDescription() {
        return drugDescription;
    }

    public void setDrugDescription(String drugDescription) {
        this.drugDescription = drugDescription;
    }
}

