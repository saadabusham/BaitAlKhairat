package com.saad.baitalkhairat.model.account;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.auth.User;

import java.io.Serializable;

public class ProfileResponse implements Serializable {

    @SerializedName("profile")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}