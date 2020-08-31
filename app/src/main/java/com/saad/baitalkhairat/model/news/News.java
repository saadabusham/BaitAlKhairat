package com.saad.baitalkhairat.model.news;

import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News implements Serializable {

    @SerializedName("is_urgent")
    private int isUrgent;

    @SerializedName("link")
    private String link;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("published_at")
    private String publishedAt;

    public int getIsUrgent() {
        return isUrgent == 1 ? View.VISIBLE : View.GONE;
    }

    public String getLink() {
        return link;
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

    public String getPublishedAt() {
        return publishedAt;
    }
}