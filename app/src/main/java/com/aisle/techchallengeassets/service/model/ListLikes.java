package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListLikes {
    @SerializedName("profiles")
    private List<Likes> profiles;

    @SerializedName("can_see_profile")
    private boolean can_see_profile;

    public List<Likes> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Likes> profiles) {
        this.profiles = profiles;
    }

    public boolean isCan_see_profile() {
        return can_see_profile;
    }

    public void setCan_see_profile(boolean can_see_profile) {
        this.can_see_profile = can_see_profile;
    }
}
