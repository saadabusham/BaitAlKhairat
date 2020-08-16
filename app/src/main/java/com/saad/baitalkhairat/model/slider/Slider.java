package com.saad.baitalkhairat.model.slider;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.Image;

import java.io.Serializable;

public class Slider implements Serializable {

    @SerializedName("image")
    private Image image;

    @SerializedName("is_show_button")
    private boolean isShowButton;

    @SerializedName("hero")
    private String hero;

    @SerializedName("button_link")
    private String buttonLink;

    @SerializedName("id")
    private int id;

    @SerializedName("button_label")
    private String buttonLabel;

    @SerializedName("sub_hero")
    private String subHero;

    public Image getImage() {
        return image;
    }

    public boolean isIsShowButton() {
        return isShowButton;
    }

    public String getHero() {
        return hero;
    }

    public String getButtonLink() {
        return buttonLink;
    }

    public int getId() {
        return id;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public String getSubHero() {
        return subHero;
    }
}