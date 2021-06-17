package com.aisle.techchallengeassets.service.model;

import com.google.gson.annotations.SerializedName;

public class UserResp {
    @SerializedName("invites")
    private ListProfile invites;
    @SerializedName("likes")
    private ListLikes likes;

    public ListProfile getInvites() {
        return invites;
    }

    public void setInvites(ListProfile invites) {
        this.invites = invites;
    }

    public ListLikes getLikes() {
        return likes;
    }

    public void setLikes(ListLikes likes) {
        this.likes = likes;
    }
}
