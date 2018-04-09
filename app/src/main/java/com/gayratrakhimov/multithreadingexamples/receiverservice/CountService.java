package com.gayratrakhimov.multithreadingexamples.receiverservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountService extends Service {

    public static final String TAG = CountService.class.getSimpleName();
    public static final String TIME = "TIME";
    private ScheduledExecutorService mScheduledExecutorService;

    public CountService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        mScheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: "+System.currentTimeMillis());
                Intent intentToSend = new Intent(SimpleReceiver.SIMPLE_ACTION);
                intentToSend.putExtra(TIME, System.currentTimeMillis());
                sendBroadcast(intentToSend);
            }
        },1000, 4000, TimeUnit.MILLISECONDS);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mScheduledExecutorService.shutdownNow();
        Log.d(TAG, "onDestroy: ");
    }

}
