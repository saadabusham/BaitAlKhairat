package com.saad.baitalkhairat.model.donors;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.Case;
import com.saad.baitalkhairat.model.listdetails.Links;
import com.saad.baitalkhairat.model.listdetails.Meta;

import java.io.Serializable;
import java.util.ArrayList;

public class CasesResponse implements Serializable {

    @SerializedName("data")
    ArrayList<Case> data;

    @SerializedName("links")
    Links links;
    @SerializedName("meta")
    Meta meta;

    public ArrayList<Case> getData() {
        return data;
    }

    public Links getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }
}
