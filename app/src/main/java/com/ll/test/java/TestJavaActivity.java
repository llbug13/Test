package com.ll.test.java;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.IDNA;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ll.test.R;
import com.ll.test.base.BActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ll on 16-12-20.
 */

public class TestJavaActivity extends BActivity {
    private TextView java_test_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        java_test_text = (TextView) findViewById(R.id.java_test_text);
//        java_test_text.setText("Product Model: " + android.os.Build.MODEL + ","
//                + android.os.Build.VERSION.SDK + ","+android.os.Build.VERSION.SDK_INT+""
//                + android.os.Build.VERSION.RELEASE);
        java_test_text.setText(getFilesDir() + Test_io.showUserInfo() + Environment.getExternalStorageDirectory() + "" + Environment.getExternalStorageState());
//        Test_io.data();
        Runtime rt = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = rt.exec("file ramdisk.img");
        } catch (IOException e) {
//            e.printStackTrace();
        }
        LocationManager locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else
//            locMgr.getLastKnownLocation();
            if (proc != null) {
                InputStream is = proc.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;
                StringBuffer s = new StringBuffer();
                try {
                    while ((line = br.readLine()) != null) {
                        s.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                java_test_text.setText(s.toString());
            }
    }
}
