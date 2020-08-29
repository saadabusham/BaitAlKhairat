package com.saad.baitalkhairat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    private static User objUser = null;
    @SerializedName("id")
    private long userID;
    @SerializedName("email")
    private String email = "";
    @SerializedName("avatar")
    String avatar = "";
    @SerializedName("name")
    private String name = "";
    @SerializedName("gender")
    private int gender;
    @SerializedName("phone")
    private String phone = "";
    @SerializedName("country_code")
    private String country_code;
    @SerializedName("birth_date")
    private String birth_date;
    @SerializedName("nationality")
    private String nationality;
    @SerializedName("country_of_residence")
    private String country_of_residence;
    @SerializedName("password")
    private String password = "";
    @SerializedName("password_confirmation")
    private String password_confirmation = "";
    @SerializedName("terms_of_uses")
    boolean terms_of_uses;
    @SerializedName("verificationCode")
    private String verificationCode;

    @SerializedName("tokenObject")
    TokenResponse tokenResponse;

    private User() {

    }

    public static User getInstance() {
        if (objUser == null)
            objUser = new User();
        return objUser;
    }

    public static User getObjUser() {
        return objUser;
    }

    public void setObjUser(User objUser) {
        User.objUser = objUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getUserName() {
        return getName();
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountry_of_residence() {
        return country_of_residence;
    }

    public void setCountry_of_residence(String country_of_residence) {
        this.country_of_residence = country_of_residence;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }


    public TokenResponse getTokenResponse() {
        return tokenResponse;
    }

    public void setTokenResponse(TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
    }

    public boolean isTerms_of_uses() {
        return terms_of_uses;
    }

    public void setTerms_of_uses(boolean terms_of_uses) {
        this.terms_of_uses = terms_of_uses;
    }
}
