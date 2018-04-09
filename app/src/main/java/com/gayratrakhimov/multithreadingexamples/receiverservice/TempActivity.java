package com.gayratrakhimov.multithreadingexamples.receiverservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gayratrakhimov.multithreadingexamples.R;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        TextView text = findViewById(R.id.text);

        long timestamp = getIntent().getLongExtra(CountService.TIME, 0);
        text.setText("Time:"+timestamp);

    }

}
