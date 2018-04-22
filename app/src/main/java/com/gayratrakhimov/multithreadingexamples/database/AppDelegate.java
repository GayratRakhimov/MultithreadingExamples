package com.gayratrakhimov.multithreadingexamples.database;

import android.app.Application;
import android.arch.persistence.room.Room;

public class AppDelegate extends Application {

    private MusicDatabase mMusicDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mMusicDatabase = Room.databaseBuilder(this, MusicDatabase.class, "music_database")
                .allowMainThreadQueries()
                .build();

    }

    public MusicDatabase getMusicDatabase() {
        return mMusicDatabase;
    }

}
