package com.android.renly.meetingreservation.api.bean;

public class SimpleUser {
    /**
     * 简化版用户类
     */
    private String name;

    private String avatar;

    public SimpleUser(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
