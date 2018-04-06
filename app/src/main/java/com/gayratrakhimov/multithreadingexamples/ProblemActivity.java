package com.gayratrakhimov.multithreadingexamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class ProblemActivity extends AppCompatActivity {

    // this example demonstrates threading problem

    private static int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int x = 0; x < 10; x++) {
            new Thread(() -> {
                for (int y = 0; y < 100; y++) {
                    mCount++;
                    System.out.println(mCount + " " + Thread.currentThread().getName());
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

}
