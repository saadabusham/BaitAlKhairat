package com.saad.baitalkhairat.model.donors;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyDonors implements Serializable {

    @SerializedName("donation_amount")
    private String donationAmount;

    @SerializedName("status_label")
    private String statusLabel;

    @SerializedName("country")
    private String country;

    @SerializedName("country_label")
    private String countryLabel;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("type_label")
    private String typeLabel;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private int type;

    @SerializedName("need_request_amount")
    private String needRequestAmount;

    @SerializedName("status")
    private int status;

    public String getDonationAmount() {
        return donationAmount;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryLabel() {
        return countryLabel;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getCaseId() {
        return String.valueOf(id);
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public String getNeedRequestAmount() {
        return needRequestAmount;
    }

    public int getStatus() {
        return status;
    }
}