package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

public class UploadImage {
    @SerializedName("status")
    public boolean status;
    @SerializedName("data")
    public String data;
    @SerializedName("message")
    public String message;

    public boolean getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
