package com.example.quang.project_sdo.Models;

import java.util.Date;

/**
 * Created by Quang on 4/22/2018.
 */

public class ChatDetailModel {
    public String idUser;
    public String idSeller;
    public String imgUser;
    public String imgSeller;
    public String chatUser;
    public String chatSeller;
    public boolean IsSender;
    public String nameUser;
    public String nameSeller;
    private long CreatedDate;

    public ChatDetailModel() {

    }

    public ChatDetailModel(String idUser, String idSeller, String imgUser, String imgSeller, String chatUser, String chatSeller, boolean isSender, String nameUser, String nameSeller) {
        this.idUser = idUser;
        this.idSeller = idSeller;
        this.imgUser = imgUser;
        this.imgSeller = imgSeller;
        this.chatUser = chatUser;
        this.chatSeller = chatSeller;
        IsSender = isSender;
        this.nameUser = nameUser;
        this.nameSeller = nameSeller;
        CreatedDate = new Date().getTime();

    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(String idSeller) {
        this.idSeller = idSeller;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getImgSeller() {
        return imgSeller;
    }

    public void setImgSeller(String imgSeller) {
        this.imgSeller = imgSeller;
    }

    public String getChatUser() {
        return chatUser;
    }

    public void setChatUser(String chatUser) {
        this.chatUser = chatUser;
    }

    public String getChatSeller() {
        return chatSeller;
    }

    public void setChatSeller(String chatSeller) {
        this.chatSeller = chatSeller;
    }

    public boolean isSender() {
        return IsSender;
    }

    public void setSender(boolean sender) {
        IsSender = sender;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public long getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(long createdDate) {
        CreatedDate = createdDate;
    }

}
