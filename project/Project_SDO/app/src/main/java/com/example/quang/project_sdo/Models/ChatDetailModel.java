package com.example.quang.project_sdo.Models;

import java.util.Date;

/**
 * Created by Quang on 4/22/2018.
 */

public class ChatDetailModel {
    private String UserName;
    private String Message;
    private int Avatar;
    private boolean IsSender;
    private long CreatedDate;

    public ChatDetailModel() {
        IsSender = true;
    }

    public ChatDetailModel(String name, String message, int avatar, boolean isSender) {
        UserName = name;
        Message = message;
        Avatar = avatar;
        IsSender = isSender;
        CreatedDate = new Date().getTime();
    }


    public void setName(String name) {
        UserName = name;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }

    public void setSender(boolean sender) {
        IsSender = sender;
    }

    public String getName() {

        return UserName;
    }

    public String getMessage() {
        return Message;
    }

    public int getAvatar() {
        return Avatar;
    }

    public boolean isSender() {
        return IsSender;
    }

    public long getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(long createdDate) {
        CreatedDate = createdDate;
    }
}
