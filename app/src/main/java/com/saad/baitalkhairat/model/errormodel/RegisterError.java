package com.saad.baitalkhairat.model.errormodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RegisterError implements Serializable {

    @SerializedName("country_code")
    private List<String> countryCode;

    @SerializedName("password")
    private List<String> password;

    @SerializedName("gender")
    private List<String> gender;

    @SerializedName("nationality")
    private List<String> nationality;

    @SerializedName("country_of_residence")
    private List<String> countryOfResidence;

    @SerializedName("phone")
    private List<String> phone;

    @SerializedName("birth_date")
    private List<String> birthDate;

    @SerializedName("name")
    private List<String> name;

    @SerializedName("email")
    private List<String> email;

    @SerializedName("verificationCode")
    private List<String> verificationCode;

    public List<String> getCountryCode() {
        return countryCode;
    }

    public List<String> getPassword() {
        return password;
    }

    public List<String> getGender() {
        return gender;
    }

    public List<String> getNationality() {
        return nationality;
    }

    public List<String> getCountryOfResidence() {
        return countryOfResidence;
    }

    public List<String> getPhone() {
        return phone;
    }

    public List<String> getBirthDate() {
        return birthDate;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getEmail() {
        return email;
    }

    public List<String> getVerificationCode() {
        return verificationCode;
    }

    @Override
    public String toString() {
        return "RegisterError{" +
                "countryCode=" + countryCode +
                ", password=" + password +
                ", gender=" + gender +
                ", nationality=" + nationality +
                ", countryOfResidence=" + countryOfResidence +
                ", phone=" + phone +
                ", birthDate=" + birthDate +
                ", name=" + name +
                ", email=" + email +
                ", verificationCode=" + verificationCode +
                '}';
    }
}
