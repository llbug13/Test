package com.ll.test.show;

import android.app.Activity;
import android.os.Bundle;

import com.ll.test.R;

public class MyOtherActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}