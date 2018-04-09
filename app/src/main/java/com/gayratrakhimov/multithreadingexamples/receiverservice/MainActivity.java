package com.gayratrakhimov.multithreadingexamples.receiverservice;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gayratrakhimov.multithreadingexamples.R;

public class MainActivity extends AppCompatActivity {

    private SimpleReceiver simpleReceiver;
    private IntentFilter intentFilter;

    private Button btnSendBroadcast;
    private Button startServiceBtn;
    private Button stopServiceBtn;
    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = findViewById(R.id.tv_time);
        btnSendBroadcast = findViewById(R.id.btn_send_broadcast);
        startServiceBtn = findViewById(R.id.btn_start_service);
        stopServiceBtn = findViewById(R.id.btn_stop_service);

        simpleReceiver = new SimpleReceiver(tvTime);
        intentFilter = new IntentFilter(SimpleReceiver.SIMPLE_ACTION);

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcast(new Intent(SimpleReceiver.SIMPLE_ACTION));
            }
        });

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CountService.class);
                startService(intent);
            }
        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CountService.class);
                stopService(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(simpleReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(simpleReceiver);
    }

}
