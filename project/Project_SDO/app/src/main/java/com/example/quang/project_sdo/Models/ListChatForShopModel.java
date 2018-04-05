package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/5/2018.
 */

public class ListChatForShopModel {
    public String nameUser;
    public String recentChat;
    public int avatarUser;

    public ListChatForShopModel(String nameUser, String recentChat, int avatarUser) {
        this.nameUser = nameUser;
        this.recentChat = recentChat;
        this.avatarUser = avatarUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getRecentChat() {
        return recentChat;
    }

    public void setRecentChat(String recentChat) {
        this.recentChat = recentChat;
    }

    public int getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(int avatarUser) {
        this.avatarUser = avatarUser;
    }
}
