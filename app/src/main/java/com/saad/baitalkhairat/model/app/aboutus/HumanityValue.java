package com.saad.baitalkhairat.model.app.aboutus;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.Image;

import java.io.Serializable;

public class HumanityValue implements Serializable {

    @SerializedName("image")
    private Image image;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public Image getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}