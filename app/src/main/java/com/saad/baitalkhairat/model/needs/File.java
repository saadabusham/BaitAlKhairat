package com.saad.baitalkhairat.model.needs;

import com.google.gson.annotations.SerializedName;

public class File {

    @SerializedName("path")
    private String path;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("id")
    private int id;

    public String getPath() {
        return path;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getId() {
        return id;
    }
}