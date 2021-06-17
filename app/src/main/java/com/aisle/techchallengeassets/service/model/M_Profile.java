package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class M_Profile {
    @SerializedName("general_information")
    private GeneralInfo general_information;
    @SerializedName("photos")
    private List<Photos> photos;

    public GeneralInfo getGeneral_information() {
        return general_information;
    }

    public void setGeneral_information(GeneralInfo general_information) {
        this.general_information = general_information;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }
}
