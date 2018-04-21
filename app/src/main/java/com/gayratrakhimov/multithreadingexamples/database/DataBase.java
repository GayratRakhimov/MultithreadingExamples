package com.gayratrakhimov.multithreadingexamples.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Song.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract MusicDao getMusicDao();
}