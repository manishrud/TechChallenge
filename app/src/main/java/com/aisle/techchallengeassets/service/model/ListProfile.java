package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListProfile {
    @SerializedName("profiles")
    private ArrayList<M_Profile> profiles;

    public ArrayList<M_Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<M_Profile> profiles) {
        this.profiles = profiles;
    }
}
