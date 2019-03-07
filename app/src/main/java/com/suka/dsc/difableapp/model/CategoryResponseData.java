package com.suka.dsc.difableapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CategoryResponseData implements Parcelable {
    @SerializedName("category")
    private String category;

    public String getCategory() {
        return category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.category);
    }

    public CategoryResponseData() {
    }

    protected CategoryResponseData(Parcel in) {
        this.category = in.readString();
    }

    public static final Parcelable.Creator<CategoryResponseData> CREATOR = new Parcelable.Creator<CategoryResponseData>() {
        @Override
        public CategoryResponseData createFromParcel(Parcel source) {
            return new CategoryResponseData(source);
        }

        @Override
        public CategoryResponseData[] newArray(int size) {
            return new CategoryResponseData[size];
        }
    };
}
