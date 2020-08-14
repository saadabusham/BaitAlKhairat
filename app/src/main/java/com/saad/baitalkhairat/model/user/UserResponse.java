package com.saad.baitalkhairat.model.user;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.ListItem;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {

    @SerializedName("social_youtube_link")
    private String socialYoutubeLink;

    @SerializedName("gender")
    private int gender;

    @SerializedName("country_of_residence")
    private String countryOfResidence;

    @SerializedName("documents")
    private List<Object> documents;

    @SerializedName("birth_date")
    private String birthDate;

    @SerializedName("education_start_date")
    private String educationStartDate;

    @SerializedName("description")
    private String description;

    @SerializedName("work_site")
    private String workSite;

    @SerializedName("work_start_date")
    private String workStartDate;

    @SerializedName("marital_status_label")
    private String maritalStatusLabel;

    @SerializedName("education_specialty")
    private String educationSpecialty;

    @SerializedName("work_place")
    private String workPlace;

    @SerializedName("country_of_residence_label")
    private String countryOfResidenceLabel;

    @SerializedName("profile_image")
    private ProfileImage profileImage;

    @SerializedName("social_instagram_link")
    private String socialInstagramLink;

    @SerializedName("id")
    private int id;

    @SerializedName("education_country")
    private String educationCountry;

    @SerializedName("email")
    private String email;

    @SerializedName("gender_label")
    private String genderLabel;

    @SerializedName("website")
    private String website;

    @SerializedName("nationality_label")
    private String nationalityLabel;

    @SerializedName("education_country_label")
    private String educationCountryLabel;

    @SerializedName("social_linkedin_link")
    private String socialLinkedinLink;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("social_facebook_link")
    private String socialFacebookLink;

    @SerializedName("education_end_date")
    private String educationEndDate;

    @SerializedName("work_end_date")
    private String workEndDate;

    @SerializedName("marital_status")
    private String maritalStatus;

    @SerializedName("nationality")
    private String nationality;

    @SerializedName("work_address")
    private String workAddress;

    @SerializedName("name")
    private String name;

    @SerializedName("education_university")
    private String educationUniversity;

    @SerializedName("social_twitter_link")
    private String socialTwitterLink;


    public int getMyGender(List<ListItem> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(String.valueOf(gender))) {
                return i + 1;
            }
        }
        return 0;
    }

    public int getMyMarital(List<ListItem> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(String.valueOf(maritalStatus))) {
                return i + 1;
            }
        }
        return 0;
    }


    public int getMyCountry(List<ListItem> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue().equals(getCountryOfResidence())) {
                return i + 1;
            }
        }
        return 0;
    }

    public String getSocialYoutubeLink() {
        return socialYoutubeLink;
    }

    public int getGender() {
        return gender;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public List<Object> getDocuments() {
        return documents;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEducationStartDate() {
        return educationStartDate;
    }

    public String getDescription() {
        return description;
    }

    public String getWorkSite() {
        return workSite;
    }

    public String getWorkStartDate() {
        return workStartDate;
    }

    public String getMaritalStatusLabel() {
        return maritalStatusLabel;
    }

    public String getEducationSpecialty() {
        return educationSpecialty;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public String getCountryOfResidenceLabel() {
        return countryOfResidenceLabel;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public String getSocialInstagramLink() {
        return socialInstagramLink;
    }

    public int getId() {
        return id;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public String getEducationCountry() {
        return educationCountry;
    }

    public String getEmail() {
        return email;
    }

    public String getGenderLabel() {
        return genderLabel;
    }

    public String getWebsite() {
        return website;
    }

    public String getNationalityLabel() {
        return nationalityLabel;
    }

    public String getEducationCountryLabel() {
        return educationCountryLabel;
    }

    public String getSocialLinkedinLink() {
        return socialLinkedinLink;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSocialFacebookLink() {
        return socialFacebookLink;
    }

    public String getEducationEndDate() {
        return educationEndDate;
    }

    public String getWorkEndDate() {
        return workEndDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public String getName() {
        return name;
    }

    public String getEducationUniversity() {
        return educationUniversity;
    }

    public String getSocialTwitterLink() {
        return socialTwitterLink;
    }

    public String getBirthYear() {
        return birthDate.split("-")[0];
    }

    public String getBirthMonth() {
        return birthDate.split("-")[1];
    }

    public String getBirthDay() {
        return birthDate.split("-")[2];
    }
}