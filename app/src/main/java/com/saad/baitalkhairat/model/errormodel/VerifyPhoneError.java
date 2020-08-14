package com.saad.baitalkhairat.model.errormodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VerifyPhoneError implements Serializable {

    @SerializedName("country_code")
    private List<String> country_code;

    @SerializedName("phone")
    private List<String> phone;

    @SerializedName("verificationCode")
    private List<String> verificationCode;

    public List<String> getCountry_code() {
        return country_code;
    }

    public void setCountry_code(List<String> country_code) {
        this.country_code = country_code;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(List<String> verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "VerifyPhoneError{" +
                "country_code=" + country_code +
                ", phone=" + phone +
                '}';
    }
}
