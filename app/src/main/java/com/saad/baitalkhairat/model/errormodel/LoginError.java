package com.saad.baitalkhairat.model.errormodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoginError implements Serializable {

    @SerializedName("password")
    private List<String> password;

    @SerializedName("phone")
    private List<String> phone;

    public List<String> getPassword() {
        return password;
    }

    public List<String> getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "LoginError{" +
                "password=" + password +
                ", phone=" + phone +
                '}';
    }
}
