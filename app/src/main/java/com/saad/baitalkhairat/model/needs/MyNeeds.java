package com.saad.baitalkhairat.model.needs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyNeeds implements Serializable {

    @SerializedName("country")
    private String country;

    @SerializedName("amount")
    private String amount;


    @SerializedName("description")
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private int type;

    @SerializedName("status_label")
    private String statusLabel;

    @SerializedName("country_label")
    private String countryLabel;

    @SerializedName("id")
    private int id;

    @SerializedName("type_label")
    private String typeLabel;

    @SerializedName("delivered_at")
    private Object deliveredAt;

    @SerializedName("status")
    private int status;

    public String getCountry() {
        return country;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public String getCountryLabel() {
        return countryLabel;
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

    public Object getDeliveredAt() {
        return deliveredAt;
    }

    public int getStatus() {
        return status;
    }
}