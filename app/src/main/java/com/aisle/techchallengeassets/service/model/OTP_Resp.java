package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class OTP_Resp {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @SerializedName("token")
    private String token;
}
