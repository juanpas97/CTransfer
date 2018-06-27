package com.example.juanperezdealgaba.ctransfer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.snatik.storage.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private static final int REQUEST_WRITE_STORAGE = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method


        boolean hasPermission = (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }


        Storage storage = new Storage(getApplicationContext());

        String path = storage.getExternalStorageDirectory();

        final String newDir = path + File.separator + "Ctransfer";
        storage.createDirectory(newDir);

        final File report = new File(newDir, "Report_128.txt");

        if (report.exists())
            report.delete();

        final Button completeTest = findViewById(R.id.Start_Test);
        completeTest.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("StaticFieldLeak")
            public void onClick(View v) {

                try {
                    ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                    dialog.setMessage("Starting");
                    dialog.show();

                    FileWriter writer = new FileWriter(report);

                    RandomStringGenerator string = new RandomStringGenerator();

                    String test = string.generateRandomString(128);

                    for (int i = 0; i < 100000; i++){

                        long start = System.nanoTime();

                        String result = test(test);

                        long end = System.nanoTime();

                        long result_time = end - start;
                        writer.write("Time to perform: " + result_time + " nanoseconds\n");
                    }
                    writer.close();
                    System.out.println("Task finished");
                    dialog.dismiss();
                }catch (IOException i){
                    throw new RuntimeException(i);
                }


            }
        });


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String test(String test);

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "The app was not allowed to write to your storage. " +
                            "Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }


        }


    }
}