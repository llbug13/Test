package com.ll.test;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.ll.test.activity.CycleActivity;
import com.ll.test.log.L;
import com.ll.test.view.CompassView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private QuickContactBadge badge;
    private CompassView l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        badge = (QuickContactBadge) findViewById(R.id.adge);
        l1 = (CompassView) findViewById(R.id.l1);
        l1.setOnClickListener(this);
        badge.assignContactFromPhone("123123131", false);
//        badge.assignContactUri(Uri.parse("http://www.baidu.com"));
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

    private float r = 0;

    @Override
    public void onClick(View v) {
        r += 15;
        L.i("aaaarrrrrrrrr" + r);
        l1.setBearing(r);
    }
}
