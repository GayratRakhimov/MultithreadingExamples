package com.gayratrakhimov.multithreadingexamples.receiverservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.gayratrakhimov.multithreadingexamples.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountService extends Service {

    public static final String TAG = CountService.class.getSimpleName();
    public static final String TIME = "TIME";
    private ScheduledExecutorService mScheduledExecutorService;

    private NotificationManager manager;
    private NotificationCompat.Builder builder;

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

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = getNotificationBuilder();

        builder.setContentTitle("Count service notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground);

    }

    private NotificationCompat.Builder getNotificationBuilder() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
            return new NotificationCompat.Builder(this);
        } else {
            String channelId = "my_channel_id";

            if (manager.getNotificationChannel(channelId) == null) {
                NotificationChannel channel = new NotificationChannel(channelId, "Text for user", NotificationManager.IMPORTANCE_LOW);
                manager.createNotificationChannel(channel);
            }

            return new NotificationCompat.Builder(this, channelId);
        }
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        startForeground(123, getNotification("start notification"));

        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + System.currentTimeMillis());

                manager.notify(123, getNotification("time is "+System.currentTimeMillis()));

                Intent intentToSend = new Intent(SimpleReceiver.SIMPLE_ACTION);
                intentToSend.putExtra(TIME, System.currentTimeMillis());
                sendBroadcast(intentToSend);
            }
        }, 1000, 4000, TimeUnit.MILLISECONDS);

        return START_STICKY;
    }

    private Notification getNotification(String contentText) {

        Intent intent = new Intent(this, TempActivity.class);
        intent.putExtra(TIME, System.currentTimeMillis());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2343, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return builder.setContentText(contentText)
                .setContentIntent(pendingIntent)
                .build();
    }

    @Override
    public void onDestroy() {
        mScheduledExecutorService.shutdownNow();
        Log.d(TAG, "onDestroy: ");
    }

}
