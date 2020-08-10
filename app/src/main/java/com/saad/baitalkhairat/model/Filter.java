package com.saad.baitalkhairat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Filter implements Serializable {

    @SerializedName("gender")
    String gender;

    @SerializedName("country")
    String country;

}
