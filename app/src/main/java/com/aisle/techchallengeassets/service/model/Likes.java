package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class Likes {
    @SerializedName("first_name")
    private String name;
    @SerializedName("avatar")
    private String avatar;

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
