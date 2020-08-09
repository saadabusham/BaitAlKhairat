package com.saad.baitalkhairat.model.donors;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.Category;

import java.io.Serializable;
import java.util.List;

public class CategoryResponse implements Serializable {

    @SerializedName("selected_value")
    private String selectedValue;

    @SerializedName("list")
    private List<Category> list;

    public String getSelectedValue() {
        return selectedValue;
    }

    public List<Category> getList() {
        return list;
    }
}