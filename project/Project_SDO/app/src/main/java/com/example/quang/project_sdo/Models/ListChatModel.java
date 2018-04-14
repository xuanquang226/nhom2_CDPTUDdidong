package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/5/2018.
 */

public class ListChatModel {
    public String name;
    public String recentChat;
    public int avatar;

    public ListChatModel(String name, String recentChat, int avatar) {
        this.name = name;
        this.recentChat = recentChat;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecentChat() {
        return recentChat;
    }

    public void setRecentChat(String recentChat) {
        this.recentChat = recentChat;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}


