package com.saad.baitalkhairat.model.app.termsofuse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TermsOfUse implements Serializable {

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