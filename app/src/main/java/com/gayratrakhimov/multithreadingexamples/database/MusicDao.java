package com.gayratrakhimov.multithreadingexamples.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao //обозначаем класс для работы с таблицами
public interface MusicDao {

    //добавить песни в таблицу
    @Insert
    public void insertSongs(List<Song> songs);

    //получить список всех песен из таблицы  @Query( " SELECT * from song")
    public List<Song> getSongs();

    //удалить песню по id
    @Query("DELETE FROM song WHERE id = :songId")
    void deleteSongById(int songId);

}