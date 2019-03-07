package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    public boolean statusLogin;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public UserLoginDetail data = new UserLoginDetail();

    public boolean getStatusLogin() {
        return statusLogin;
    }

    public String getMessage() {
        return message;
    }

    public UserLoginDetail getData() {
        return data;
    }

    public class UserLoginDetail {
        @SerializedName("uid")
        public String user_id;
        @SerializedName("role")
        public String user_role;

        public String getUserId() {
            return user_id;
        }

        public String getUserRole() {
            return user_role;
        }
    }
}
