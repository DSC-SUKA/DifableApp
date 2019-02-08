package com.suka.dsc.difableapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookModel {
    @SerializedName("allbooks")
    private List<BookData> mBookData;

    public BookModel(List<BookData> bookData) {
        this.mBookData = bookData;
    }

    public List<BookData> getmBookData() {
        return mBookData;
    }

    public void setmBookData(List<BookData> mBookData) {
        this.mBookData = mBookData;
    }
}
