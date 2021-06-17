package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class OtpRequest {
    @SerializedName("number")
    private String number;
    @SerializedName("otp")
    private String otp;

    public OtpRequest() {
    }

    public OtpRequest(String number, String otp) {
        this.number = number;
        this.otp = otp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
