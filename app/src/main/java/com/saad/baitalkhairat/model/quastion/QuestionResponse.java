package com.saad.baitalkhairat.model.quastion;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.listdetails.Links;
import com.saad.baitalkhairat.model.listdetails.Meta;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionResponse implements Serializable {

    @SerializedName("data")
    ArrayList<Question> data;

    @SerializedName("links")
    Links links;
    @SerializedName("meta")
    Meta meta;

    public ArrayList<Question> getData() {
        return data;
    }

    public Links getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }
}
