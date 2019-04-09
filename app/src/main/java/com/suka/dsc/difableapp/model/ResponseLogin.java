package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("status")
    private boolean statusLogin;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private UserLoginDetail data = new UserLoginDetail();

    public boolean getStatusLogin() {
        return statusLogin;
    }

    public String getMessage() {
        return message;
    }

    public UserLoginDetail getData() {
        return data;
    }
}
