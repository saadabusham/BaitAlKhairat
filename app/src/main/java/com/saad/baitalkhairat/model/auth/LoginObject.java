package com.saad.baitalkhairat.model.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginObject implements Serializable {

    @SerializedName("phone")
    String phone;

    @SerializedName("country_code")
    String country_code;

    @SerializedName("password")
    String password;

    @SerializedName("platform-id")
    String platform;

    public LoginObject(String phone, String country_code, String password, String platform) {
        this.phone = phone;
        this.country_code = country_code;
        this.password = password;
        this.platform = platform;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
