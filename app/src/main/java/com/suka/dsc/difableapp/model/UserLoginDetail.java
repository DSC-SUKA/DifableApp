package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

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
