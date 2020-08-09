package com.saad.baitalkhairat.model.slider;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.listdetails.Links;
import com.saad.baitalkhairat.model.listdetails.Meta;

import java.io.Serializable;
import java.util.ArrayList;

public class SliderResponse implements Serializable {

    @SerializedName("data")
    ArrayList<Slider> data;

    @SerializedName("links")
    Links links;
    @SerializedName("meta")
    Meta meta;

    public ArrayList<Slider> getData() {
        return data;
    }

    public Links getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }
}
