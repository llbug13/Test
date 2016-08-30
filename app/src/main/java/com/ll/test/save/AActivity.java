package com.ll.test.save;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by LL on 2016/8/30.
 */
public class AActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        没有显示的结束（调用finish）
//        硬件配置改变，如屏幕改变是可以使用savedInstanceState
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
