package com.ll.test.map;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;

import com.ll.test.R;

/**
 * Created by LL on 2016/9/13.
 */
public class MapTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    private void getmap() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }


}
