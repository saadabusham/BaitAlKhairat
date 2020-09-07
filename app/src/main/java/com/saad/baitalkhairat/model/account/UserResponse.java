package com.saad.baitalkhairat.model.account;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.File;
import com.saad.baitalkhairat.model.ListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserResponse implements Serializable {

    private static MutableLiveData<UserResponse> userResponseMutableLiveData = new MutableLiveData<>();
    @SerializedName("social_youtube_link")
    private String socialYoutubeLink;

    @SerializedName("gender")
    private int gender;

    @SerializedName("country_of_residence")
    private String countryOfResidence;

    @SerializedName("binding_key")
    String binding_key;

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

    @SerializedName("documents")
    private ArrayList<File> documents;

    @SerializedName("delete_documents")
    private ArrayList<String> deletedDocuments;

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

    public int getStudyCountry(List<ListItem> list) {
        if (getEducationCountry() != null && !getEducationCountry().isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getValue().equals(getEducationCountry())) {
                    return i + 1;
                }
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

    public List<File> getDocuments() {
        if (documents == null)
            documents = new ArrayList<>();
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

    public static MutableLiveData<UserResponse> getUserResponseMutableLiveData() {
        return userResponseMutableLiveData;
    }

    public String getEducationStartYear() {
        return educationStartDate != null && !educationStartDate.isEmpty() ?
                educationStartDate.split("-")[0] : "";
    }

    public String getEducationStartMonth() {
        return educationStartDate != null && !educationStartDate.isEmpty() ?
                educationStartDate.split("-")[1] : "";
    }

    public String getEducationStartDay() {
        return educationStartDate != null && !educationStartDate.isEmpty() ?
                educationStartDate.split("-")[2] : "";
    }

    public String getEducationEndYear() {
        return educationEndDate != null && !educationEndDate.isEmpty() ?
                educationEndDate.split("-")[0] : "";
    }

    public String getEducationEndMonth() {
        return educationEndDate != null && !educationEndDate.isEmpty() ?
                educationEndDate.split("-")[1] : "";
    }

    public String getEducationEndDay() {
        return educationEndDate != null && !educationEndDate.isEmpty() ?
                educationEndDate.split("-")[2] : "";
    }

    public void setSocialYoutubeLink(String socialYoutubeLink) {
        this.socialYoutubeLink = socialYoutubeLink;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEducationStartDate(String educationStartDate) {
        this.educationStartDate = educationStartDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite;
    }

    public void setWorkStartDate(String workStartDate) {
        this.workStartDate = workStartDate;
    }

    public void setMaritalStatusLabel(String maritalStatusLabel) {
        this.maritalStatusLabel = maritalStatusLabel;
    }

    public void setEducationSpecialty(String educationSpecialty) {
        this.educationSpecialty = educationSpecialty;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public void setCountryOfResidenceLabel(String countryOfResidenceLabel) {
        this.countryOfResidenceLabel = countryOfResidenceLabel;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public void setSocialInstagramLink(String socialInstagramLink) {
        this.socialInstagramLink = socialInstagramLink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEducationCountry(String educationCountry) {
        this.educationCountry = educationCountry;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGenderLabel(String genderLabel) {
        this.genderLabel = genderLabel;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setNationalityLabel(String nationalityLabel) {
        this.nationalityLabel = nationalityLabel;
    }

    public void setEducationCountryLabel(String educationCountryLabel) {
        this.educationCountryLabel = educationCountryLabel;
    }

    public void setSocialLinkedinLink(String socialLinkedinLink) {
        this.socialLinkedinLink = socialLinkedinLink;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSocialFacebookLink(String socialFacebookLink) {
        this.socialFacebookLink = socialFacebookLink;
    }

    public void setEducationEndDate(String educationEndDate) {
        this.educationEndDate = educationEndDate;
    }

    public void setWorkEndDate(String workEndDate) {
        this.workEndDate = workEndDate;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEducationUniversity(String educationUniversity) {
        this.educationUniversity = educationUniversity;
    }

    public void setSocialTwitterLink(String socialTwitterLink) {
        this.socialTwitterLink = socialTwitterLink;
    }

    public String getBinding_key() {
        return binding_key;
    }

    public void setBinding_key(String binding_key) {
        this.binding_key = binding_key;
    }

    public ArrayList<String> getDeletedDocuments() {
        if (deletedDocuments == null)
            deletedDocuments = new ArrayList<>();
        return deletedDocuments;
    }

    public void setDeletedDocuments(ArrayList<String> deletedDocuments) {
        this.deletedDocuments = deletedDocuments;
    }

    public static void setUserResponseMutableLiveData(MutableLiveData<UserResponse> userResponseMutableLiveData) {
        UserResponse.userResponseMutableLiveData = userResponseMutableLiveData;
    }

    public void setDocuments(ArrayList<File> documents) {
        this.documents = documents;
        getUserResponseMutableLiveData().postValue(this);
    }

    public ArrayList<File> removeDocument(int id) {
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getId() == id) {
                documents.remove(i);
            }
        }
        return documents;
    }
}