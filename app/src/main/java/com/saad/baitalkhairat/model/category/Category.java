package com.saad.baitalkhairat.model.category;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.Image;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("color")
    String color;

    @SerializedName("id")
    private int value;
    @SerializedName("image")
    Image image;
    @SerializedName("title")
    private String label;

    public Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return label;
    }

}
