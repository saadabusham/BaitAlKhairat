package com.saad.baitalkhairat.model;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.errormodel.LoginError;

public class TokenResponse {

    @SerializedName("token_generated_date")
    long token_generated_date;
    @SerializedName("errors")
    LoginError loginError;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getToken_generated_date() {
        return token_generated_date;
    }

    public void setToken_generated_date(long token_generated_date) {
        this.token_generated_date = token_generated_date;
    }

    public LoginError getLoginError() {
        return loginError;
    }

    public void setLoginError(LoginError loginError) {
        this.loginError = loginError;
    }
}