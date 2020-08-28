package com.saad.baitalkhairat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Filter implements Serializable {

    @SerializedName("type")
    int type;

    @SerializedName("gender")
    String gender = "";

    @SerializedName("country")
    String country = "";

    public void setFilter(Filter filter, boolean withType) {
        setGender(filter.getGender());
        setCountry(filter.getCountry());
        if (withType)
            setType(filter.getType());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
