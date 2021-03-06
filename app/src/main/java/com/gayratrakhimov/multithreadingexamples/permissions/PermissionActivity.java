package com.gayratrakhimov.multithreadingexamples.permissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gayratrakhimov.multithreadingexamples.R;

public class PermissionActivity extends AppCompatActivity {

    public static final int WRITE_PERMISSION_RC = 123;
    String mTextToWrite = "Test text to write on file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        writeToFileWithPermissionRequestIfNeeded(mTextToWrite);

    }

    private void writeToFileWithPermissionRequestIfNeeded(String textToWrite) {
        if (isWritePermissionGranted()) {
            writeToFile(textToWrite);
        } else {
            requestWritePermission();
        }
    }

    public boolean isWritePermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestWritePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setMessage("Permission for writing file is required")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_RC);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_RC);
        }
    }

    private void writeToFile(String textToWrite) {
        Toast.makeText(this, textToWrite + " is written on file", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != WRITE_PERMISSION_RC) return;
        if (grantResults.length != 1) return;

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            writeToFile(mTextToWrite);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("You can give permission in settings anytime")
                    .setPositiveButton("OK", null).show();
        }

    }

}
