package com.gayratrakhimov.multithreadingexamples.database;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gayratrakhimov.multithreadingexamples.R;
import com.gayratrakhimov.multithreadingexamples.database.model.Album;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        MusicDatabase mDatabase = Room.databaseBuilder(getApplicationContext(), MusicDatabase.class, "music_database") // название файла
                .fallbackToDestructiveMigration() // дешевый способ миграции на новую версию
                .build();

        MusicDao musicDao = ((AppDelegate)getApplicationContext()).getMusicDatabase().getMusicDao();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicDao.insertAlbums(createAlbums());
            }
        });

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast(musicDao.getAlbums());
            }
        });

    }

    private List<Album> createAlbums() {
        List<Album> albums = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            albums.add(new Album(i, "album "+i, "release "+System.currentTimeMillis()));
        }
        return albums;
    }

    private void showToast(List<Album> albums) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < albums.size(); i++) {
            builder.append(albums.get(i).toString()).append("\n");
        }
        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
    }

}
