package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponses {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<CategoryResponseData> data = new ArrayList<>();

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<CategoryResponseData> getData() {
        return data;
    }


}
