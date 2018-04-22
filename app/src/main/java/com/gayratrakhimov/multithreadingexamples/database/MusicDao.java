package com.gayratrakhimov.multithreadingexamples.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gayratrakhimov.multithreadingexamples.database.model.Album;
import com.gayratrakhimov.multithreadingexamples.database.model.Song;

import java.util.List;

@Dao //обозначаем класс для работы с таблицами
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<Album> albums);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSongs(List<Song> songs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setLinksAlbumSongs(List<Album> linksAlbumSongs);

    @Query( " SELECT * from album")
    public List<Album> getAlbums();

    @Query( " SELECT * from song")
    public List<Song> getSongs();

    @Delete
    void deleteAlbum(Album album);

    @Query("SELECT * FROM song INNER JOIN albumsong on song.id = albumsong.song_id WHERE album_id = :albumId")
    void getSongsFromAlbum(int albumId);



}