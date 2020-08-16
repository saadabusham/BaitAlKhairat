package com.saad.baitalkhairat.model.app.aboutus;

import com.google.gson.annotations.SerializedName;

public class BaitAlKhairatResources {

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