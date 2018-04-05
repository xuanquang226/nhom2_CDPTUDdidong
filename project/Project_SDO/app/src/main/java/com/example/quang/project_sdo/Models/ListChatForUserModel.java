package com.example.quang.project_sdo.Models;

/**
 * Created by Trang on 4/5/2018.
 */

public class ListChatForUserModel {
    public String nameShop;
    public String recentChatS;
    public int avatarShop;

    public ListChatForUserModel(String nameShop, String recentChatS, int avatarShop) {
        this.nameShop = nameShop;
        this.recentChatS = recentChatS;
        this.avatarShop = avatarShop;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getRecentChatS() {
        return recentChatS;
    }

    public void setRecentChatS(String recentChatS) {
        this.recentChatS = recentChatS;
    }

    public int getAvatarShop() {
        return avatarShop;
    }

    public void setAvatarShop(int avatarShop) {
        this.avatarShop = avatarShop;
    }
}
