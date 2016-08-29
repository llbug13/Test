package com.ll.test.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;

/**
 * Created by LL on 2016/8/25.
 */
public class StickyIntent {
    //    BroadcastReceiver的变体保存最后一次广播的值
    {
        Context context = null;
        IntentFilter battery = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent cyr = context.registerReceiver(null, battery);
//        动态的注册,BroadcastReceiver是不必须的在sticky
//        当intent时可以获取intent
        Intent intent = new Intent();
//        <uses-permissionandroid:name="android.permission.BROADCAST_STICKY"/>
        context.sendStickyBroadcast(intent);
        context.removeStickyBroadcast(intent);
    }

    //    得到电量，不用在manifest注册
    private void getm(Context context) {
//        ACTION_BATTERY_LOW
//        ACTION_BATTERY_OKAY
//        ACTION_POWER_CONNECTED
//        ACTION_POWER_DISCONNECTED
        IntentFilter battery1 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent battery = context.registerReceiver(null, battery1);
        int status = battery.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging =
                status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;

    }

    //得到网络状态//需要权限
    private void getNetworkSate(Context context) {
        String svcName = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(svcName);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        是否连接
        boolean isConnected = activeNetwork.isConnectedOrConnecting();
//        是否是手机连接
        boolean isMobile = activeNetwork.getType() ==
                ConnectivityManager.TYPE_MOBILE;
    }

    //    监听Dock变化
    private void getDockSate(Context context) {
        IntentFilter dockIntentFilter =
                new IntentFilter(Intent.ACTION_DOCK_EVENT);
        Intent dock = context.registerReceiver(null, dockIntentFilter);
//        得到dock的状态
        int dockdockState = dock.getIntExtra(Intent.EXTRA_DOCK_STATE,
                Intent.EXTRA_DOCK_STATE_UNDOCKED);
        boolean isDocked = dockdockState != Intent.EXTRA_DOCK_STATE_UNDOCKED;

    }

}
