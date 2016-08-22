package com.ll.test;

import android.app.Activity;
import android.os.Bundle;

import com.ll.test.log.L;

/**
 * Created by LL on 2016/8/22.
 */
public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t);
    }

    @Override
    protected void onDestroy() {
        L.i("onDestroyonDestroyonDestroyonDestroyonDestroy");
        super.onDestroy();
    }
    @Override
    public void finish() {
        L.i("onDestroyfinishfinishfinish");
        super.finish();
    }
}
