package com.example.quang.project_sdo.Models;

import java.util.Date;

/**
 * Created by Trang on 4/5/2018.
 */

public class ListChatModel {
    public String idUser;
    public String idSeller;
    public String imgUser;
    public String imgSeller;
    public String chatUser;
    public String chatSeller;
    public String nameUser;
    public String nameSeller;

    public ListChatModel() {

    }

    public ListChatModel(String idUser, String idSeller, String imgUser, String imgSeller, String chatUser, String chatSeller,String nameUser, String nameSeller) {
        this.idUser = idUser;
        this.idSeller = idSeller;
        this.imgUser = imgUser;
        this.imgSeller = imgSeller;
        this.chatUser = chatUser;
        this.chatSeller = chatSeller;
        this.nameUser = nameUser;
        this.nameSeller = nameSeller;

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
}


