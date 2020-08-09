package com.saad.baitalkhairat.model.listdetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Links implements Serializable {

    @SerializedName("next")
    private String next;

    @SerializedName("last")
    private String last;

    @SerializedName("prev")
    private String prev;

    @SerializedName("first")
    private String first;

    public String getNext() {
        return next;
    }

    public String getLast() {
        return last;
    }

    public String getPrev() {
        return prev;
    }

    public String getFirst() {
        return first;
    }
}