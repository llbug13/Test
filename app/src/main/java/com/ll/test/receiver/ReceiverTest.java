package com.ll.test.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by LL on 2016/8/25.
 */
public class ReceiverTest extends BroadcastReceiver {
    //    代码注册
//    manifest注册（接收器）不一定在只可以在运输状态时才嗯呢接收，有匹配的intent自动启动
    @Override
    public void onReceive(Context context, Intent intent) {
//        intent.getData
//        context.sendBroadcast(intent,"");
//        有序广播 Android：priority值越大优先级越高
//        可以接到intent的数据
//        context.sendOrderedBroadcast(intent,"");
//        context.sendOrderedBroadcast();
//        响应接受到的intent
//        响应在5秒完成
        Uri data = intent.getData();
        Intent intent1 = new Intent("com.ll.action.LL", data);
        context.startActivity(intent);
//        在代码中注册时，只有在程序组件运行时响应intent
//        一般在onResume中处理程序中注册接收器，在onPause中注销它
    }

    {
//        Intent动作字符串可以标示要广播的事件，唯一字符串
//        Intent intent = new Intent("com.paad.action.NEW_LIFEFORM");
    }
}
