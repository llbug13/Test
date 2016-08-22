package com.ll.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ll.test.R;
import com.ll.test.log.L;

/**
 * Created by LL on 2016/8/22.
 */
public class CycleActivity extends Activity {
    private TextView ll;

    private void i(String msg) {
        L.i(getClass().getName(), msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t);
        ll = (TextView) findViewById(R.id.ll);
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
        i("onStart" + ll.getHeight());
    }

    @Override
    protected void onResume() {
        super.onResume();
        i("onResume" + ll.getHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        i("onWindowFocusChanged" + ll.getHeight());
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
