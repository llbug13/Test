package com.ll.test.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by LL on 2016/8/25.
 */
public class StickyIntent {
    //    BroadcastReceiver的变体保存最后一次广播的值
    {
        Context context = null;
        IntentFilter battery = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent cyr = context.registerReceiver(null, battery);
//        动态的注册
//        当intent时可以获取intent
        Intent intent = new Intent();
//        <uses-permissionandroid:name="android.permission.BROADCAST_STICKY"/>
        context.sendStickyBroadcast(intent);
        context.removeStickyBroadcast(intent);
    }


}
