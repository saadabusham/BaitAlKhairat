package com.saad.baitalkhairat.model.country.countrycode;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CountryCodeResponse implements Serializable {

    @SerializedName("selected_value")
    private String selectedValue;

    @SerializedName("list")
    private List<ListItem> list;

    public String getSelectedValue() {
        return selectedValue;
    }

    public List<ListItem> getList() {
        return list;
    }
}