package com.saad.baitalkhairat.model.app.privacypolicy;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PrivacyPolicy implements Serializable {

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;


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