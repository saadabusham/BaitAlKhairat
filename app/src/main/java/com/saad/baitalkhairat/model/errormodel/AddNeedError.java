package com.saad.baitalkhairat.model.errormodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddNeedError implements Serializable {

    @SerializedName("country")
    private List<String> country;

    @SerializedName("binding_key")
    private List<String> bindingKey;

    @SerializedName("amount")
    private List<String> amount;

    @SerializedName("description")
    private List<String> description;

    @SerializedName("title")
    private List<String> title;

    @SerializedName("type")
    private List<String> type;

    public List<String> getCountry() {
        return country;
    }

    public List<String> getBindingKey() {
        return bindingKey;
    }

    public List<String> getAmount() {
        return amount;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getTitle() {
        return title;
    }

    public List<String> getType() {
        return type;
    }
}