package com.gayratrakhimov.multithreadingexamples.database;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gayratrakhimov.multithreadingexamples.R;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        DataBase mDatabase = Room.databaseBuilder(getApplicationContext(), DataBase.class, "music_database") // название файла
                .fallbackToDestructiveMigration() // дешевый способ миграции на новую версию
                .build();

    }

}
