package com.suka.dsc.difableapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AllbookCategoriesData implements Parcelable {
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

    public AllbookCategoriesData() {
    }

    protected AllbookCategoriesData(Parcel in) {
        this.id = in.readString();
        this.bookCategory = in.readString();
    }

    public static final Parcelable.Creator<AllbookCategoriesData> CREATOR = new Parcelable.Creator<AllbookCategoriesData>() {
        @Override
        public AllbookCategoriesData createFromParcel(Parcel source) {
            return new AllbookCategoriesData(source);
        }

        @Override
        public AllbookCategoriesData[] newArray(int size) {
            return new AllbookCategoriesData[size];
        }
    };
}
