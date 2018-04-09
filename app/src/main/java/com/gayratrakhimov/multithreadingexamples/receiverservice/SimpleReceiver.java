package com.gayratrakhimov.multithreadingexamples.receiverservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class SimpleReceiver extends BroadcastReceiver {

    private WeakReference<TextView> textViewWeakReference;

    public SimpleReceiver(TextView textView) {
        this.textViewWeakReference = new WeakReference<TextView>(textView);
    }

    public static final String SIMPLE_ACTION = "com.gayratrakhimov.multithreadingexamples.receiver.SIMPLE_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        long timestamp = intent.getLongExtra(CountService.TIME, 0);
        Toast.makeText(context, intent.getAction()+":"+timestamp, Toast.LENGTH_LONG).show();

        TextView textView = textViewWeakReference.get();
        textView.setText("Time:"+timestamp);

//        Intent launchActivityIntent = new Intent(context, TempActivity.class);
//        launchActivityIntent.putExtra(CountService.TIME, System.currentTimeMillis());
//        context.startActivity(launchActivityIntent);

    }

}
