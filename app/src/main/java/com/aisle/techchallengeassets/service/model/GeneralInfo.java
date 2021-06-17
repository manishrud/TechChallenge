package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class GeneralInfo {
    @SerializedName("first_name")
    private String first_name;

    @SerializedName("age")
    private String age;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
