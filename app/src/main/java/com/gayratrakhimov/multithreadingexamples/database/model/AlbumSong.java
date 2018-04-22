package com.gayratrakhimov.multithreadingexamples.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Album.class, parentColumns = "id", childColumns = "album_id"),
        @ForeignKey(entity = Song.class, parentColumns = "id", childColumns = "song_id")
})
public class AlbumSong {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "album_id")
    private int mAlbumId;

    @ColumnInfo(name = "song_id")
    private int mSongId;

}
