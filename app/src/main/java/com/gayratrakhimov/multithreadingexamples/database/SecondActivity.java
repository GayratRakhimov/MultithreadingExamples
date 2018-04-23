package com.gayratrakhimov.multithreadingexamples.database;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.gayratrakhimov.multithreadingexamples.R;

public class SecondActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(123, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, Uri.parse("content://com.gayratrakhimov.multithreadingexamples.musicprovider/album"), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder builder = new StringBuilder();
            do {
                builder.append(cursor.getString(cursor.getColumnIndex("name"))).append("\n");
            } while (cursor.moveToNext());
            Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
