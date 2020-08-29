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

    @SerializedName("terms_of_uses")
    private List<String> terms_of_uses;

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
        StringBuilder stringBuilder = new StringBuilder();
        if (countryCode != null)
            stringBuilder.append("countryCode=" + countryCode);
        if (gender != null)
            stringBuilder.append("gender=" + gender);
        if (nationality != null)
            stringBuilder.append("nationality=" + nationality);
        if (countryOfResidence != null)
            stringBuilder.append("countryOfResidence=" + countryOfResidence);
        if (birthDate != null)
            stringBuilder.append("birthDate=" + birthDate);
        if (verificationCode != null)
            stringBuilder.append("verificationCode=" + verificationCode);
        if (terms_of_uses != null)
            stringBuilder.append("termsOfUser=" + terms_of_uses);

        return stringBuilder.toString();
    }
}
