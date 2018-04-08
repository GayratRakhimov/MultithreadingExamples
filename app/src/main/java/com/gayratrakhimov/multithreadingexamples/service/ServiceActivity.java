package com.gayratrakhimov.multithreadingexamples.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gayratrakhimov.multithreadingexamples.R;

public class ServiceActivity extends AppCompatActivity {

    private Button startServiceBtn;
    private Button stopServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        startServiceBtn = findViewById(R.id.btn_start_service);
        stopServiceBtn = findViewById(R.id.btn_stop_service);

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceActivity.this, CountService.class);
                startService(intent);
            }
        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceActivity.this, CountService.class);
                stopService(intent);
            }
        });

    }

}
