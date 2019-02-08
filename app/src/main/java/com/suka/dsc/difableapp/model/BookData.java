package com.suka.dsc.difableapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BookData implements Parcelable {
    @SerializedName("title")
    private String bookTitle;

    @SerializedName("idCategory")
    private String categoryId;

    @SerializedName("nameCategory")
    private String categoryName;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookTitle);
        dest.writeString(this.categoryId);
        dest.writeString(this.categoryName);
    }

    public BookData() {
    }

    protected BookData(Parcel in) {
        this.bookTitle = in.readString();
        this.categoryId = in.readString();
        this.categoryName = in.readString();
    }

    public static final Parcelable.Creator<BookData> CREATOR = new Parcelable.Creator<BookData>() {
        @Override
        public BookData createFromParcel(Parcel source) {
            return new BookData(source);
        }

        @Override
        public BookData[] newArray(int size) {
            return new BookData[size];
        }
    };
}
