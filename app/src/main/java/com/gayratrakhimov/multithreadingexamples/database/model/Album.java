package com.gayratrakhimov.multithreadingexamples.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Album {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "release")
    private String mReleaseDate;

    public Album(){

    }

    public Album(int mId, String mName, String mReleaseDate) {
        this.mId = mId;
        this.mName = mName;
        this.mReleaseDate = mReleaseDate;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    @Override
    public String toString() {
        return "Album{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                '}';
    }

}
