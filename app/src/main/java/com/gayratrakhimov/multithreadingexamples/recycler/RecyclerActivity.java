package com.gayratrakhimov.multithreadingexamples.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gayratrakhimov.multithreadingexamples.R;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        if(savedInstanceState==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, RecyclerFragment.newInstance())
                    .commit();
        }

    }

}
