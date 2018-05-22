package com.example.athis.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String mName;
    private String mId;

    public Book(String name, String id){
        this.mName = name;
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setId(String id) {
        this.mId = id;
    }

    protected Book(Parcel in) {
        mId = in.readString();
        mName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
    }

    @Override
    public String toString() {
        return "ID: "+ mId + "  Name" + mName;
    }
}
