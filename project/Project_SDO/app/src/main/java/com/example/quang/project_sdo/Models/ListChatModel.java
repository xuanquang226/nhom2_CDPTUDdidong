package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/5/2018.
 */

public class ListChatModel {
    public String name;
    public String message;
    public String avatar;
    public String id;
    public String idShop;
    public String nameShop;

    public ListChatModel() {
    }

    public ListChatModel(String name, String message, String avatar,String id,String idShop,String nameShop) {
        this.name = name;
        this.message = message;
        this.avatar = avatar;
        this.id = id;
        this.idShop = idShop;
        this.nameShop = nameShop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }
}


