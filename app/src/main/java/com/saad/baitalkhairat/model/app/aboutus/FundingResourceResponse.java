package com.saad.baitalkhairat.model.app.aboutus;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.listdetails.Links;
import com.saad.baitalkhairat.model.listdetails.Meta;

import java.io.Serializable;
import java.util.ArrayList;

public class FundingResourceResponse implements Serializable {

    @SerializedName("data")
    ArrayList<FundingResource> data;

    @SerializedName("links")
    Links links;
    @SerializedName("meta")
    Meta meta;

    public ArrayList<FundingResource> getData() {
        return data;
    }

    public Links getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }
}
