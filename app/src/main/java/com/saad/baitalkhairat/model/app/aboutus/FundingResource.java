package com.saad.baitalkhairat.model.app.aboutus;

import com.google.gson.annotations.SerializedName;

public class FundingResource {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;


    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}