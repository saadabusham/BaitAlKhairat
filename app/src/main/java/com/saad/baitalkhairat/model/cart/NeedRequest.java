package com.saad.baitalkhairat.model.cart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NeedRequest implements Serializable {

    @SerializedName("country")
    private String country;

    @SerializedName("amount")
    private String amount;

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

    public String getCountry() {
        return country;
    }

    public String getAmount() {
        return amount;
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
}