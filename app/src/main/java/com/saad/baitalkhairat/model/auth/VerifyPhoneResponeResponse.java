package com.saad.baitalkhairat.model.auth;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.errormodel.VerifyPhoneError;

import java.io.Serializable;

public class VerifyPhoneResponeResponse implements Serializable {

    @SerializedName("errors")
    VerifyPhoneError verifyPhoneError;
    @SerializedName("token")
    private String token;
    @SerializedName("mobile")
    private String mobile;

    public String getToken() {
        return token;
    }

    public String getMobile() {
        return mobile;
    }

    public VerifyPhoneError getVerifyPhoneError() {
        return verifyPhoneError;
    }
}