package com.saad.baitalkhairat.model.app;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AboutUs implements Serializable {

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}