package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookCategoriesModel {
    @SerializedName("categories")
    private List<BookCategoriesData> mAllbookItems;

    public BookCategoriesModel(List<BookCategoriesData> allbookItems){
        this.mAllbookItems = allbookItems;
    }

    public List<BookCategoriesData> getmAllbookItems() {
        return mAllbookItems;
    }

    public void setmAllbookItems(List<BookCategoriesData> mAllbookItems) {
        this.mAllbookItems = mAllbookItems;
    }
}
