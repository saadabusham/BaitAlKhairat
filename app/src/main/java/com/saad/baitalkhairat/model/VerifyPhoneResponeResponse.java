package com.saad.baitalkhairat.model;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.errormodel.VerifyPhoneError;

public class VerifyPhoneResponeResponse {

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