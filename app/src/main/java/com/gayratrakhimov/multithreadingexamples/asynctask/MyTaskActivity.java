package com.gayratrakhimov.multithreadingexamples.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gayratrakhimov.multithreadingexamples.R;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MyTaskActivity extends AppCompatActivity {

    private DownloadImageTask mDownloadImageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String url = "http://i0.kym-cdn.com/entries/icons/mobile/000/013/564/doge.jpg";
        mDownloadImageTask = new DownloadImageTask(MyTaskActivity.this);

        Button startBtn = findViewById(R.id.btn_start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadImageTask.execute(url);
            }
        });
        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadImageTask.cancel(true);
            }
        });
    }

    public ImageView getImageView() {
        return findViewById(R.id.imageView);
    }

    public ProgressBar getProgressBar() {
        return findViewById(R.id.progressBar);
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private WeakReference<MyTaskActivity> mActivityWeakReference;
        private DownloadImageTask(MyTaskActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }
        @Override
        protected void onPreExecute() {
            MyTaskActivity activity = mActivityWeakReference.get();
            if (activity != null)
                activity.getProgressBar().setVisibility(View.VISIBLE);
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            return getBitmap(strings[0]);
        }
        private Bitmap getBitmap(String url) {
            try {
                TimeUnit.SECONDS.sleep(5);
                if (isCancelled()) {
                    return null;
                }
                InputStream is = (InputStream) new URL(url).getContent();
                Bitmap d = BitmapFactory.decodeStream(is);
                is.close();
                return d;
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            MyTaskActivity activity = mActivityWeakReference.get();
            if (activity != null) {
                activity.getImageView().setImageBitmap(bitmap);
                activity.getProgressBar().setVisibility(View.INVISIBLE);
            }
        }
        @Override
        protected void onCancelled() {
            MyTaskActivity mainActivity = mActivityWeakReference.get();
            if (mainActivity != null) {
                mainActivity.getProgressBar().setVisibility(View.INVISIBLE);
                Toast.makeText(mainActivity, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
