package com.saad.baitalkhairat.model.needs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddNeed implements Serializable {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("amount")
    double amount;

    @SerializedName("type")
    int caseType;

    @SerializedName("country")
    String country;

    @SerializedName("degree")
    int degree;

    @SerializedName("binding_key")
    String binding_key;

    public AddNeed(String title, String description, double amount, int caseType, String country, int degree, String binding_key) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.caseType = caseType;
        this.country = country;
        this.degree = degree;
        this.binding_key = binding_key;
    }
}
