package com.saad.baitalkhairat.model.app;

import com.google.gson.annotations.SerializedName;

public class AppBank {

    @SerializedName("account_number")
    private String accountNumber;

    @SerializedName("country")
    private String country;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("branch")
    private String branch;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }
}