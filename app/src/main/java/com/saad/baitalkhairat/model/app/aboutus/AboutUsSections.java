package com.saad.baitalkhairat.model.app.aboutus;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.Image;

import java.io.Serializable;

public class AboutUsSections implements Serializable {

    @SerializedName("image")
    private Image image;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public Image getImage() {
        return image;
    }

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