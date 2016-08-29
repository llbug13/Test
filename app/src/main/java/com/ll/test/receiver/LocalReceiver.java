package com.ll.test.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by LL on 2016/8/25.
 */
public class LocalReceiver {
    //    局部广播
//    在应用程序组件之间
//    更高效，更安全，发送和接受不到其他程序的局部广播
    {
        Context context = null;
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
//        注册
        manager.registerReceiver(new ReceiverTest(), new IntentFilter(""));
        manager.sendBroadcast(new Intent());
    }
}
