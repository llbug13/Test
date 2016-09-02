package com.ll.test.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

/**
 * Created by LL on 2016/9/2.
 */
public class TestActivity extends Activity {

    private void testRun() {
//    强制更新ui
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//            更新ui
            }
        });
        handler.post(doUpdateGUI);
    }

    private Handler handler = new Handler();

    // Runnable that executes the updateGUI method.
    private Runnable doUpdateGUI = new Runnable() {
        public void run() {
            updateGUI();
        }
    };

    // This method must be called on the UI thread.
    private void updateGUI() {
        // [ ... Open a dialog or modify a GUI element ... ]
    }

    private void bindToService() {
        //Bind to the service
//        绑定一个service
        Intent bindIntent = new Intent(this, ServivceTest.class);
        bindService(bindIntent, mConnection, Context.BIND_AUTO_CREATE);
//        Context.BIND_ADJUST_WITH_ACTIVITY根据activity的重要程度来调整这个service的优先级
//        Context.BIND_IMPORTANT;Context.BIND_ABOVE_CLIENT对于正在绑定一个service的客服端来说，这个service非常重要，，当客服端处于前台时，serv也应变成前台进程
//        BIND_ABOVE_CLIENT在内存低的时候，会再终止绑定的service之前先终止activity
//        Context.BIND_NOT_FOREGROUND,service永远不应该拥有运行于前台的优先级
//        Context.BIND_WAIVE_PRIORITY初步改变service的优先级

    }

    private ServivceTest serviceRef;

    //Handles the connection between the service and activity
//    连接
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // Called when the connection is made.
//            当连接时调用，获取service的引用
            serviceRef = ((ServivceTest.MyBinder) service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            // Received when the service unexpectedly disconnects.
//            意外断开连接时接收
            serviceRef = null;
        }
    };

}
