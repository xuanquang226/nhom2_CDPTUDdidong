package com.example.quang.project_sdo.Models;

import java.util.Date;

/**
 * Created by Quang on 4/22/2018.
 */

public class ChatDetailModel {
    private String UserName;
    private String Message;
    private String Avatar;
    private boolean IsSender;
    private long CreatedDate;
    private String idShop;
    private String ID;
    private String nameShop;
    private String nameDefault;

    public ChatDetailModel() {

    }

    public ChatDetailModel(String name, String message, String avatar, boolean isSender, String idshop, String id,String namshop,String namedefault) {
        UserName = name;
        Message = message;
        Avatar = avatar;
        IsSender = isSender;
        CreatedDate = new Date().getTime();
        idShop = idshop;
        ID = id;
        nameShop = namshop;
        nameDefault = namedefault;
    }

    public String getName() {

        return UserName;
    }
    public void setName(String name) {
        UserName = name;
    }
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
    public String getAvatar() {
        return Avatar;
    }
    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public boolean isSender() {
        return IsSender;
    }
    public void setSender(boolean sender) {
        IsSender = sender;
    }

    public long getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(long createdDate) {
        CreatedDate = createdDate;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getNameDefault() {
        return nameDefault;
    }

    public void setNameDefault(String nameDefault) {
        this.nameDefault = nameDefault;
    }
}
