package com.saad.baitalkhairat.model.wallet;

import com.google.gson.annotations.SerializedName;

public class CheckCharge {

    @SerializedName("amount")
    private String amount;

    @SerializedName("charge_type")
    private String chargeType;

    public String getAmount() {
        return amount;
    }

    public String getChargeType() {
        return chargeType;
    }
}