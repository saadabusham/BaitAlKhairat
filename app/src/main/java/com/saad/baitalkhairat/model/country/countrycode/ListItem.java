package com.saad.baitalkhairat.model.country.countrycode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListItem implements Serializable {

    @SerializedName("label")
    private String label;

    @SerializedName("value")
    private String value;

    public ListItem(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label;
    }
}