package com.ll.test.activity;

import android.app.Activity;
import android.os.Bundle;

import com.ll.test.log.L;

/**
 * Created by LL on 2016/8/22.
 */
public class CycleActivity extends Activity {
    private void i(String msg) {
        L.i(getClass().getName(), msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i("onCreate");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        i("onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        i("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        i("onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        i("onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        i("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        i("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        i("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        i("onDestroy");
    }
}
