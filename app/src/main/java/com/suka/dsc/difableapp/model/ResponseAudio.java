package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseAudio {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<AudioResponsesData> data;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<AudioResponsesData> getData() {
        return data;
    }

}
