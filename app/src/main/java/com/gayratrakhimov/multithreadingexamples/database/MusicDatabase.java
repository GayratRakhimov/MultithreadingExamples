package com.gayratrakhimov.multithreadingexamples.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gayratrakhimov.multithreadingexamples.database.model.Album;
import com.gayratrakhimov.multithreadingexamples.database.model.AlbumSong;
import com.gayratrakhimov.multithreadingexamples.database.model.Song;

@Database(entities = {Song.class, Album.class, AlbumSong.class}, version = 1)
public abstract class MusicDatabase extends RoomDatabase {
    public abstract MusicDao getMusicDao();
}