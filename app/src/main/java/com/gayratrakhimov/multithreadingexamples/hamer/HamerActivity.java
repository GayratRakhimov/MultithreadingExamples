package com.gayratrakhimov.multithreadingexamples.hamer;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.gayratrakhimov.multithreadingexamples.R;

public class HamerActivity extends AppCompatActivity implements ImageProcessThread.Callback {

    private ImageView mDoge;
    private ProgressBar mProgressBar;
    private ImageProcessThread mImageProcessThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamer);

        final Button performBtn = findViewById(R.id.btn_perform);
        mDoge = findViewById(R.id.iv_doge);
        mProgressBar = findViewById(R.id.progress);
//создаем новый экземпляр фонового потока потока
        mImageProcessThread = new ImageProcessThread("Background "); //запускаем поток и инициализируем Looper
        mImageProcessThread.start();
        mImageProcessThread.getLooper(); // -> вызовется onLooperPrepared()
        mImageProcessThread.setCallback(this);
        performBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//выдергиваем Bitmap из ImageView и скармливаем в наш поток, //просто вызывая метод на нем
                BitmapDrawable drawable = (BitmapDrawable) mDoge.getDrawable();
                mImageProcessThread.performOperation(drawable.getBitmap());
            }
        });
    }

    @Override
    public void sendProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    @Override
    public void onCompleted(Bitmap bitmap) {
        mDoge.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
//гасим фоновый поток, не мусорим
        mImageProcessThread.quit();
        super.onDestroy();
    }


}
