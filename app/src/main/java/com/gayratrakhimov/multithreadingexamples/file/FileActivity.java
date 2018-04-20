package com.gayratrakhimov.multithreadingexamples.file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.gayratrakhimov.multithreadingexamples.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        InputStream inputStreamFromRawFile = getInputStreamFromRawFile(R.raw.message);
        String stringFromRawFile = getStringFromInputStream(inputStreamFromRawFile);
        TextView textView = findViewById(R.id.text);
        textView.setText(stringFromRawFile);

        InputStream inputStreamFromAssetFile = getInputStreamFromAssetFile("message");
        String stringFromAssetFile = getStringFromInputStream(inputStreamFromAssetFile);
        Toast.makeText(this, stringFromAssetFile, Toast.LENGTH_LONG).show();

    }

    private String getStringFromInputStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Writer writer = new StringWriter();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.append("\n");
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private InputStream getInputStreamFromRawFile(int rawId) {
        return getResources().openRawResource(rawId);
    }

    private InputStream getInputStreamFromAssetFile(String filename) {
        try {
            return getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
