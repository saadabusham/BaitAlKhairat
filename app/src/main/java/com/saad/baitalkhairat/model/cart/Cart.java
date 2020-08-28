package com.saad.baitalkhairat.model.cart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {

    @SerializedName("donation_amount")
    private String donationAmount;

    @SerializedName("need_request")
    private NeedRequest needRequest;

    @SerializedName("platform_id")
    private String platformId;

    @SerializedName("id")
    private int id;

    @SerializedName("need_request_amount")
    private String needRequestAmount;

    public String getDonationAmount() {
        return donationAmount;
    }

    public NeedRequest getNeedRequest() {
        return needRequest;
    }

    public String getPlatformId() {
        return platformId;
    }

    public int getId() {
        return id;
    }

    public String getNeedRequestAmount() {
        return needRequestAmount;
    }
}