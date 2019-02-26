package com.suka.dsc.difableapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BookCategoriesData implements Parcelable {
    @SerializedName("idCategory")
    private String id;

    @SerializedName("nameCategory")
    private String bookCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.bookCategory);
    }

    public BookCategoriesData() {
    }

    protected BookCategoriesData(Parcel in) {
        this.id = in.readString();
        this.bookCategory = in.readString();
    }

    public static final Parcelable.Creator<BookCategoriesData> CREATOR = new Parcelable.Creator<BookCategoriesData>() {
        @Override
        public BookCategoriesData createFromParcel(Parcel source) {
            return new BookCategoriesData(source);
        }

        @Override
        public BookCategoriesData[] newArray(int size) {
            return new BookCategoriesData[size];
        }
    };
}
