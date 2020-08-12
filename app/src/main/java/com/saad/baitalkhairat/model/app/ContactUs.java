package com.saad.baitalkhairat.model.app;

import com.google.gson.annotations.SerializedName;

public class ContactUs {

    @SerializedName("twitter_link")
    private String twitterLink = "";

    @SerializedName("post_office_box")
    private String postOfficeBox = "";

    @SerializedName("mobile")
    private String mobile = "";

    @SerializedName("facebook_link")
    private String facebookLink = "";

    @SerializedName("id")
    private int id;

    @SerializedName("linkedin_link")
    private String linkedinLink = "";

    @SerializedName("email")
    private String email = "";

    public String getTwitterLink() {
        return twitterLink;
    }

    public String getPostOfficeBox() {
        return postOfficeBox;
    }

    public String getMobile() {
        return mobile;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public int getId() {
        return id;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public String getEmail() {
        return email;
    }
}