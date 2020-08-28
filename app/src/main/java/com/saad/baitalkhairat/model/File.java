package com.saad.baitalkhairat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class File implements Serializable {


    @SerializedName("path")
    private String path;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("id")
    private int id;

    public File(String path) {
        this.path = path;
    }

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