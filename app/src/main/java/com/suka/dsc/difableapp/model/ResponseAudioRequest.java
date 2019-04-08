package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

public class ResponseAudioRequest {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
