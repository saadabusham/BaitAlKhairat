package com.saad.baitalkhairat.model.wallet;

import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Wallet implements Serializable {

    @SerializedName("balance")
    private String balance;

    @SerializedName("remaining_balance")
    private String remainingBalance;

    @SerializedName("donation_paid")
    private String donationPaid;

    @SerializedName("id")
    private int id;

    @SerializedName("is_empty")
    private boolean isEmpty;

    public String getBalance() {
        return balance;
    }

    public String getRemainingBalance() {
        return remainingBalance;
    }

    public String getDonationPaid() {
        return donationPaid;
    }

    public int getId() {
        return id;
    }

    public int isIsEmpty() {
        return isEmpty ? View.GONE : View.VISIBLE;
    }
}