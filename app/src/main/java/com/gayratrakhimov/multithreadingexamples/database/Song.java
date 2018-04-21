package com.gayratrakhimov.multithreadingexamples.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Song {

    @PrimaryKey // первичный ключ, идентификатор строки
    @ColumnInfo(name = " id")  // обозначаем столбец, задаем имя
    private int mId;

    @ColumnInfo(name = " name")
    private String mName;
    @ColumnInfo(name = " duration")
    private String mDuration;

    //тут обычные геттеры и сеттеры

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

    public String getmDuration() {
        return mDuration;
    }

    public void setmDuration(String mDuration) {
        this.mDuration = mDuration;
    }

}