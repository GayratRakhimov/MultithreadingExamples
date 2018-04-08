package com.gayratrakhimov.multithreadingexamples.multithreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SyncronizedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public synchronized void doSomething(){
        // method syncronized in object level
    }

    public void doSomethingBlock(){
        synchronized (this){
            // method syncronized in object level
        }
    }

    private final static Object lock = new Object();
    public void doSomethingStaticBlock(){
        synchronized (lock){
            // method syncronized in class level using static object lock
        }
    }

}
