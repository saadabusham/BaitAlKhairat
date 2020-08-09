package com.saad.baitalkhairat.model.listdetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meta implements Serializable {

    @SerializedName("path")
    private String path;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("total")
    private int total;

    @SerializedName("last_page")
    private int lastPage;

    @SerializedName("from")
    private int from;

    @SerializedName("to")
    private int to;

    @SerializedName("current_page")
    private int currentPage;

    public String getPath() {
        return path;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}