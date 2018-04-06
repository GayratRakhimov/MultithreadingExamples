package com.gayratrakhimov.multithreadingexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThreadActivity extends AppCompatActivity {

    // Thread creation example

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

    }

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

}
