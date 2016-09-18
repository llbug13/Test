package com.ll.test.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ll.test.log.L;

/**
 * Created by LL on 2016/9/16.
 */
public class BActivity extends AppCompatActivity {
    protected void i(String msg) {
        String name = getClass().getName();
        L.i(name, name + "::::::::::" + msg);
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
