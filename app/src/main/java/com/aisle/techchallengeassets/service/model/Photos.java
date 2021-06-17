package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
