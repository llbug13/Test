package com.ll.test;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ll.test.activity.CycleActivity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onll(View view) {
        startActivity(new Intent(MainActivity.this, CycleActivity.class));
        TextView ll = (TextView) findViewById(R.id.ll);
        Resources resources = getResources();
        String u = resources.getQuantityString(R.plurals.unicornCunt, 0);
        ll.setText(u);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//activity已经更新了
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            当是竖屏的时候
        }
    }
}
