package com.gayratrakhimov.multithreadingexamples.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gayratrakhimov.multithreadingexamples.R;

public class ReceiverActivity extends AppCompatActivity {

    private SimpleReceiver simpleReceiver;
    private IntentFilter intentFilter;
    private Button btnSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        simpleReceiver = new SimpleReceiver();
        intentFilter = new IntentFilter(SimpleReceiver.SIMPLE_ACTION);

        btnSendBroadcast = findViewById(R.id.btn_send_broadcast);

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcast(new Intent(SimpleReceiver.SIMPLE_ACTION));
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
