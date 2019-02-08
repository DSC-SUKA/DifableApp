package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllbookCategoriesModel {
    @SerializedName("categories")
    private List<AllbookCategoriesData> mAllbookItems;

    public AllbookCategoriesModel(List<AllbookCategoriesData> allbookItems){
        this.mAllbookItems = allbookItems;
    }

    public List<AllbookCategoriesData> getmAllbookItems() {
        return mAllbookItems;
    }

    public void setmAllbookItems(List<AllbookCategoriesData> mAllbookItems) {
        this.mAllbookItems = mAllbookItems;
    }
}
