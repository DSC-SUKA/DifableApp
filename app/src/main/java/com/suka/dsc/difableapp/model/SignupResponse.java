package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    public UserSignupData data = new UserSignupData();

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserSignupData getData() {
        return data;
    }

    public class UserSignupData {
        @SerializedName("uid")
        String user_id;
        @SerializedName("email")
        String email;
        @SerializedName("email_verified")
        boolean emailVerified;
        @SerializedName("displayName")
        String displayName;
        @SerializedName("photoUrl")
        String photoUrl;
        @SerializedName("phoneNumber")
        String phoneNumber;

        public String getUser_id() {
            return user_id;
        }

        public String getEmail() {
            return email;
        }

        public boolean isEmailVerified() {
            return emailVerified;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }
}
