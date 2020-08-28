package com.saad.baitalkhairat.model.cart;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.listdetails.Links;
import com.saad.baitalkhairat.model.listdetails.Meta;

import java.io.Serializable;
import java.util.ArrayList;

public class CartResponse implements Serializable {

    @SerializedName("data")
    ArrayList<Cart> data;

    @SerializedName("links")
    Links links;

    @SerializedName("meta")
    Meta meta;

    @SerializedName("total_amount")
    String total_amount;

    @SerializedName("total_item")
    int total_item;

    @SerializedName("total_item_shortcut")
    int total_item_shortcut;

    public ArrayList<Cart> getData() {
        return data;
    }

    public Links getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public int getTotal_item() {
        return total_item;
    }

    public int getTotal_item_shortcut() {
        return total_item_shortcut;
    }
}
