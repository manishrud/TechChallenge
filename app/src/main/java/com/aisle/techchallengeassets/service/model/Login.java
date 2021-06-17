package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("status")
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
