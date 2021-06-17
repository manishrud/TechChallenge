package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class LoginRquest {
    public LoginRquest(String number) {
        this.number = number;
    }

    public LoginRquest() {
    }

    @SerializedName("number")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
