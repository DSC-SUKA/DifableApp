package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("uid")
    private String userId;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("emailVerified")
    private boolean userEmailVerified;

    @SerializedName("phoneNumber")
    private String userPhone;

    @SerializedName("photoURL")
    private String userPhotoUrl;

    @SerializedName("displayName")
    private String userName;

    public UserData(String userId, String userEmail, boolean userEmailVerified, String userPhone, String userPhotoUrl, String userName) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userEmailVerified = userEmailVerified;
        this.userPhone = userPhone;
        this.userPhotoUrl = userPhotoUrl;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean getUserEmailVerified() {
        return userEmailVerified;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserEmailVerified(boolean userEmailVerified) {
        this.userEmailVerified = userEmailVerified;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
